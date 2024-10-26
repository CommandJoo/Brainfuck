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

### Information
In order to document the code better, you can use `#Documenting Comment` at the end of a line to 
Explain what said line does. Alternatively you can also do `#Documenting Comment#` to make a comment
in the Middle of the line to explain what the given block does.


### Libraries used
[JoptSimple](https://github.com/jopt-simple/jopt-simple) for the Command Line Interface
