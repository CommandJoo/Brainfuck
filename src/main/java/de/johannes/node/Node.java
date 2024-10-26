package de.johannes.node;

import de.johannes.token.Token;
import de.johannes.token.TokenType;

import java.util.LinkedList;

public class Node {
    private NodeType type;
    private int value;

    public Node(NodeType type, int value) {
        this.type = type;
        this.value = value;
    }

    public NodeType type() {
        return type;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "{\"type\": "+type.name()+", \"value\": "+value+"}";
    }



    private static Node parseWhile(LinkedList<Token> tokens) {
        LinkedList<Node> content = new LinkedList<>();
        while(!tokens.isEmpty() && tokens.getFirst().type() != TokenType.WHILE_CLOSE) {
            content.add(parseFirst(tokens));
        }
        tokens.removeFirst();
        return new NodeWhile(NodeType.WHILE, content);
    }


    public static LinkedList<Node> parseAll(LinkedList<Token> tokens) {
        LinkedList<Node> result = new LinkedList<>();
        while(!tokens.isEmpty()) {
            result.add(parseFirst(tokens));
        }
        return result;
    }

    private static Node parseFirst(LinkedList<Token> tokens) {
        Token token = tokens.removeFirst();
        if(token.type() != TokenType.WHILE_OPEN) {
            return new Node(NodeType.valueOf(token.type().name()), token.value());
        }else{
            return parseWhile(tokens);
        }
    }

}
