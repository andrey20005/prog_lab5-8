package com.itmo.prog_lab5_8.common.commands;

import com.itmo.prog_lab5_8.common.Invoker;

import java.io.*;
import java.nio.ByteBuffer;

public interface Command extends Serializable {
    void execute(Invoker invoker);
    String getResult();

    static ByteBuffer serializableToByteBuffer(Serializable serializable) throws IOException {
        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(outputStream)
        ) {
            oos.writeObject(serializable);

            byte[] array = outputStream.toByteArray();

            ByteBuffer buffer = ByteBuffer.allocate(100000);
            buffer.put(array);
            buffer.flip();
            return buffer;
        }
    }

    static Serializable byteBufferToSerializable(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        byte[] array = new byte[buffer.remaining()];
        buffer.get(array);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(array))) {
            return (Serializable) ois.readObject();
        }
    }
}
