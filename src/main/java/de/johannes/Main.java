package de.johannes;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        OptionParser parser = new OptionParser();
        OptionSpec<Void> help = parser.accepts("help", "Shows this help menu").forHelp();
        OptionSpec<File> file = parser.accepts("file", "The input Brainfuck Code").requiredUnless("help").withRequiredArg().ofType(File.class);
        OptionSet options = parser.parse(args);
        if(options.has(help)) {
            parser.printHelpOn(System.out);
        }else {
            new Brainfuck(options.valueOf(file));
        }
    }
}