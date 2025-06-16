package com.itmo.prog_lab5_8.common;

import com.itmo.prog_lab5_8.common.commands.Command;
import com.itmo.prog_lab5_8.common.util.ChannelWrapper;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ClientRequester {
    private String host;
    private int port;

    public ClientRequester(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public Command request(Command command) throws ClassNotFoundException, IOException {
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.connect(new InetSocketAddress(host, port));

        ChannelWrapper wrapper = new ChannelWrapper(clientChannel);
        wrapper.writeObject(command);
        return (Command) wrapper.readObject();
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
