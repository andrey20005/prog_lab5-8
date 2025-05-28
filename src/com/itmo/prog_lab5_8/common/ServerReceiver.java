package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.commands.Command;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ServerReceiver {
    private int port;
    protected ServerSocketChannel ssc;
    protected Selector selector;
    public ServerReceiver(int port) {
        this.port = port;
        try {
            ssc = ServerSocketChannel.open();
            try {
                ssc.bind(new InetSocketAddress(port));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ssc.configureBlocking(false);
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(Invoker invoker) {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                for (SelectionKey key : keys) {
                    System.out.println(key.toString() + " " + key.isAcceptable() + " " + key.isReadable() + " " + key.isWritable());
                    if (key.isValid()) {
                        if (key.isAcceptable()) doAccept(key, invoker);
                        if (key.isReadable()) doRead(key, invoker);
                        if (key.isWritable()) doWrite(key, invoker);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void doAccept(SelectionKey key, Invoker invoker) throws IOException {
        System.out.println("accept");
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        if (sc == null) return;
//        ClientData clientData = new ClientData();
//        key.attach(clientData);
//        System.out.println(key.attachment());
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ);
    }

    protected void doRead(SelectionKey key, Invoker invoker) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        System.out.println("read");
        ClientData clientData = new ClientData();
        key.attach(clientData);
        try {
            ByteBuffer buffer = ByteBuffer.allocate(10000);
            sc.read(buffer);
            buffer.flip();
            clientData.command = (Command) Command.byteBufferToSerializable(buffer);
//            System.out.println(command);
        } catch (ClassNotFoundException e) {
            sc.close();
            return;
        }
        clientData.command.execute(invoker);
        sc.register(key.selector(), SelectionKey.OP_WRITE);
    }

    protected void doWrite(SelectionKey key, Invoker invoker) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        System.out.println("write");
        ClientData clientData = (ClientData) key.attachment();
        ByteBuffer buffer = Command.serializableToByteBuffer(clientData.command);
        sc.write(buffer);
    }

    protected static class ClientData {
        public Command command;
    }
}
