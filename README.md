# Brainfuck Interpreter
A Simple Brainfuck Interpreter written in Java

### Functionality

```java
import java.io.OutputStream;

class TestBrainfuck {
    public static void main(String[] args) {
        //use the interpreter with a custom output stream
        OutputStream out = System.out;
        new Brainfuck(new File("hello_world.bf"), out);
        //or just do
        new Brainfuck(new File("hello_world.bf")); //to print it to System.out
    }   
}
```
or using the Command Line Interface
```bash
java -jar Brainfuck-Version-all.jar --file brainfuckcode.bf
```

### Libraries used
[JoptSimple](https://github.com/jopt-simple/jopt-simple) for the Command Line Interface
