package de.johannes.token;

import java.util.Arrays;
import java.util.LinkedList;

public class Token {

        private TokenType type;
        private int value;

        public Token(TokenType type, int value) {
            this.type = type;
            this.value = value;
        }

        public TokenType type() {
            return type;
        }

        public int value() {
            return value;
        }

        @Override
        public String toString() {
            return "{\"type\": "+type.name()+", \"value\": "+value+"}";
        }

    public static LinkedList<Token> tokenize(String code) {
        LinkedList<Token> result = new LinkedList<>();
        LinkedList<Character> input = new LinkedList<>();
        Arrays.asList(code.split("")).forEach(s -> {
            if(!s.isBlank()) input.add(s.charAt(0));
        });

        while(!input.isEmpty()) {
            Character first = input.getFirst();
            if(first == '>') {
                input.removeFirst();
                int ct = 1;
                while(!input.isEmpty() && input.getFirst() == '>') {
                    ct++;
                    input.removeFirst();
                }
                result.add(new Token(TokenType.INC_PTR, ct));
            }else if(first == '<') {
                input.removeFirst();
                int ct = 1;
                while(!input.isEmpty() && input.getFirst() == '<') {
                    ct++;
                    input.removeFirst();
                }
                result.add(new Token(TokenType.DEC_PTR, ct));
            } else if(first == '+') {
                input.removeFirst();
                int ct = 1;
                while(!input.isEmpty() && input.getFirst() == '+') {
                    ct++;
                    input.removeFirst();
                }
                result.add(new Token(TokenType.INC_CELL, ct));
            }else if(first == '-') {
                input.removeFirst();
                int ct = 1;
                while(!input.isEmpty() && input.getFirst() == '-') {
                    ct++;
                    input.removeFirst();
                }
                result.add(new Token(TokenType.DEC_CELL, ct));
            } else if(first == '.') {
                input.removeFirst();
                int ct = 1;
                while(!input.isEmpty() && input.getFirst() == '.') {
                    ct++;
                    input.removeFirst();
                }
                result.add(new Token(TokenType.OUT_CELL, ct));
            }else if(first == ',') {
                input.removeFirst();
                int ct = 1;
                while(!input.isEmpty() && input.getFirst() == ',') {
                    ct++;
                    input.removeFirst();
                }
                result.add(new Token(TokenType.IN_CELL, ct));
            } else if(first == '[') {
                input.removeFirst();
                result.add(new Token(TokenType.WHILE_OPEN, 1));
            }else if(first == ']') {
                input.removeFirst();
                result.add(new Token(TokenType.WHILE_CLOSE, 1));
            } else {
                throw new IllegalArgumentException("Invalid Token found: "+first);
            }
        }

        return result;
    }



}
