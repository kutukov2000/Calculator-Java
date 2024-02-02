package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter expression: ");
        String expression = scanner.nextLine();

        double result = Calculator.evaluateExpression(expression);
        System.out.println("Result: " + result);

        scanner.close();
    }
}
