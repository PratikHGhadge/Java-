package framework;

import java.util.Stack;

public class LearnStack {
    public static void main(String[] args) {
        Stack<String> animals = new Stack<>();
        animals.push("Lion");
        animals.push("Dog");
        animals.push("Cat");
        animals.push("Horse");

        System.out.println(" Stack " + animals);

        System.out.println(animals.peek());

        animals.pop();

        System.out.println(animals.peek());
    }
}
