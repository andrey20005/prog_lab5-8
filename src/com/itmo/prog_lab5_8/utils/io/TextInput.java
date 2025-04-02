package com.itmo.prog_lab5_8.utils.io;

import java.io.IOException;

public interface TextInput {
    public boolean ready() throws IOException;
    public String input(String prompt) throws IOException;
    public String input() throws IOException;
}
