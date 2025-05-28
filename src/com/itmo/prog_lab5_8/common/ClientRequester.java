package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.commands.Command;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;

public class ClientRequester {
    private String host;
    private int port;
    protected SocketChannel sc;

    public ClientRequester(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            sc = SocketChannel.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            sc.connect(new InetSocketAddress(host, port));
        } catch (IOException ignore) {}
    }

    public void connect() throws IOException {
        sc.connect(new InetSocketAddress(host, port));
    }

    public void connect(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        sc.connect(new InetSocketAddress(host, port));
    }

    public boolean isConnected() {return sc.isConnected();}

    public Command request(Command command) throws ClassNotFoundException, IOException {
        if(!isConnected()) connect();
        ByteBuffer buffer = Command.serializableToByteBuffer(command);
        System.out.println("write");
        sc.write(buffer);
        buffer.clear();
        System.out.println("read");
        sc.read(buffer);
        return (Command) Command.byteBufferToSerializable(buffer);
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
}
