package com.itmo.prog_lab5_8.server;

import com.itmo.prog_lab5_8.common.Invoker;
import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.util.ChannelWrapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

public class MultiprocessorServer {
    private final ExecutorService reader = Executors.newCachedThreadPool();
    private final ForkJoinPool executor = new ForkJoinPool(4);
    private final ForkJoinPool writer = new ForkJoinPool(4);
    private final Invoker invoker;

    protected ServerSocketChannel ssc;
    protected Selector selector;

    public MultiprocessorServer(int port, Invoker invoker) {
        try {
            this.invoker = invoker;
            ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(port));
            selector = Selector.open();
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Сервер запущен на порту " + port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if(key.isAcceptable() && key.isValid()) {
                        accept(key);
                    } else if (key.isReadable() && key.isValid()) {
                        reader.submit(() -> read(key));
                    }
                }
                reader.awaitTermination(10000, TimeUnit.NANOSECONDS);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        if (clientChannel == null) return;
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Принято соединение: " + clientChannel.getRemoteAddress());
    }

    protected void read(SelectionKey key) {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        if (clientChannel == null) return;
        ChannelWrapper wrapper = new ChannelWrapper(clientChannel);
        try {
            try {
                Command command = (Command) wrapper.readObject();
                System.out.println(
                        "поучена команда от пользователя " + clientChannel.getRemoteAddress() + ":\n    " + command
                );
                executor.submit(() -> execute(key, command));
            } catch (ClassNotFoundException e) {
                System.out.println("некорректное поведение от пользователя " + clientChannel.getRemoteAddress());
                clientChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                clientChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void execute(SelectionKey key, Command command) {
        command.execute(invoker);
        writer.submit(() -> write(key, command));
    }

    protected void write(SelectionKey key, Command command) {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ChannelWrapper wrapper = new ChannelWrapper(clientChannel);
        try {
            wrapper.writeObject(command);
            System.out.println("конец взаимодействия с " + clientChannel.getRemoteAddress());
            clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
