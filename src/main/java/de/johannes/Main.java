package de.johannes;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        new Brainfuck(new File("hello_world.bf"));
    }
}