package de.johannes.node;

import java.util.LinkedList;

public class NodeWhile extends Node {
        private LinkedList<Node> content;

        public NodeWhile(NodeType type, LinkedList<Node> content) {
            super(type, 0);
            this.content = content;
        }

        public LinkedList<Node> content() {
            return content;
        }

        @Override
        public String toString() {
            return "{\"type\": "+type().name()+", \"value\": "+content+"}";
        }

    }