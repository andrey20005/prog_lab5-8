package com.itmo.prog_lab5_8.common.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChannelWrapper {
    SocketChannel channel;
    ByteBuffer buffer = ByteBuffer.allocate(500);
    ByteArrayOutputStream baos;
    private final Lock readLock = new ReentrantLock();
    private final Lock writeLock = new ReentrantLock();

    public ChannelWrapper(SocketChannel channel){
        this.channel = channel;
        baos = new ByteArrayOutputStream();
    }

    public void writeObject(Serializable object) throws IOException {
        writeLock.lock();
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)
                ) {
            oos.writeObject(object);
            byte[] array = baos.toByteArray();
            ByteBuffer newBuffer = ByteBuffer.allocate(array.length);
            newBuffer.put(array);
            newBuffer.flip();
            channel.write(newBuffer);
        } finally {
            writeLock.unlock();
        }
    }

    public Object readObject() throws IOException, ClassNotFoundException {
        readLock.lock();
        try {
            while (true) {
                buffer.clear();
                int bytesRead = channel.read(buffer);
                if (bytesRead == -1) break;
                buffer.flip();
                byte[] readBytes = new byte[bytesRead];
                buffer.get(readBytes);
                baos.write(readBytes);
                if (bytesRead < buffer.capacity()) break;
            }
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
                return ois.readObject();
            }
        } finally {
            readLock.unlock();
        }
    }
}
