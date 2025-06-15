package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.util.ChannelWrapper;

import java.io.*;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class ServerReceiver {
    private int port;
    protected ServerSocketChannel ssc;
    protected Selector selector;
    public ServerReceiver(int port) {
        try {
            selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Сервер запущен на порту " + port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Invoker invoker;
    public void run(Invoker invoker) {
        this.invoker = invoker;
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                ByteBuffer buffer = ByteBuffer.allocate(10);

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();

                    if (key.isAcceptable()) {
                        doAccept(selector, key);
                    } else if (key.isReadable()) {
                        doRead(key, buffer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doAccept(Selector selector, SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Принято соединение: " + clientChannel.getRemoteAddress());
    }

    protected void doRead(SelectionKey key, ByteBuffer buffer) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        buffer.clear();

        ChannelWrapper wrapper = new ChannelWrapper(clientChannel);
        try {
            Command command = (Command) wrapper.readObject();
            System.out.println(
                    "поучена команда от пользователя " + clientChannel.getRemoteAddress() + ":\n    " + command
            );
            command.execute(invoker);
            wrapper.writeObject(command);
            System.out.println("конец взаимодействия с " + clientChannel.getRemoteAddress());
            clientChannel.close();
        } catch (ClassNotFoundException e) {
            System.out.println("некорректное поведение от пользователя " + clientChannel.getRemoteAddress());
            clientChannel.close();
        }
    }

    protected static class ClientData {
        public Command command;
    }
}
