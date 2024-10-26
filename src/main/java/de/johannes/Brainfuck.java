package de.johannes;

import de.johannes.node.Node;
import de.johannes.node.NodeType;
import de.johannes.node.NodeWhile;
import de.johannes.token.Token;
import de.johannes.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

public class Brainfuck {

    private final OutputStream out;

    private final short[] cells;
    private int ptr;

    public Brainfuck(File file) throws Exception {
        this(file, System.out);
    }

    public Brainfuck(String code) throws Exception {
        this(code, System.out);
    }

    public Brainfuck(File file, OutputStream out) throws Exception {
        this(FileUtil.readFile(file.getAbsolutePath()), out);
    }

    public Brainfuck(String code, OutputStream out) throws Exception {
        LinkedList<Token> tokens = Token.tokenize(code);
        LinkedList<Node> nodes = Node.parseAll(tokens);
        this.out = out;
        this.cells = new short[65536];
        this.ptr = 0;
        interpret(nodes);
    }

    public void interpret(LinkedList<Node> nodes) throws IOException {
        while(!nodes.isEmpty()) {
            Node node = nodes.removeFirst();
            if(node.type() == NodeType.INC_PTR) {
                for(int i = 0; i < node.value(); i++) {
                    ptr=(ptr+1<=cells.length-1) ? ptr+1 : 0;
                }
            } else if(node.type() == NodeType.DEC_PTR) {
                for(int i = 0; i < node.value(); i++) {
                    ptr=(ptr-1>=0) ? ptr-1 : cells.length-1;
                }
            } else if(node.type() == NodeType.INC_CELL) {
                for(int i = 0; i < node.value(); i++) {
                    cells[ptr] = (cells[ptr]+1<=255) ? cells[ptr] = (short) (cells[ptr]+1) : 0;
                }
            } else if(node.type() == NodeType.DEC_CELL) {
                for(int i = 0; i < node.value(); i++) {
                    cells[ptr] = (cells[ptr]-1>=0) ? cells[ptr] = (short)(cells[ptr]-1) : 255;
                }
            } else if(node.type() == NodeType.OUT_CELL) {
                for(int i = 0; i < node.value(); i++) {
                    try {
                        out.write((char)(cells[ptr]));
                    } catch(Exception ex) {
                        throw new IOException("Couldn't write value of cell: "+ptr+" to OutputStream");
                    }
                }
            } else if(node.type() == NodeType.IN_CELL) {
                for(int i = 0; i < node.value(); i++) {
                    cells[ptr] = (short)new Scanner(System.in).next().charAt(0);
                }
            } else if(node.type() == NodeType.WHILE && node instanceof NodeWhile loop) {
                while(cells[ptr] > 0) {
                    interpret(new LinkedList<>(loop.content()));//need new linked list because otherwise loop.content() will be affected by removeFirst()
                }
            } else {
                throw new IllegalStateException("Unrecognized Node found: "+node.type());
            }
        }
    }

}
