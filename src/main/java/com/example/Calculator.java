package com.example;

import java.util.Stack;

public class Calculator {
    public static double evaluateExpression(String expression) {
        char[] tokens = expression.toCharArray();

        // Stacks for numbers and operators
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            // Skip spaces
            if (tokens[i] == ' ')
                continue;

            // Handle digits and decimal point
            if (Character.isDigit(tokens[i]) || tokens[i] == '.') {
                StringBuilder sb = new StringBuilder();
                // Build the complete number
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    sb.append(tokens[i++]);
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            } else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Remove the opening parenthesis
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!operators.isEmpty() && hasPrecedence(tokens[i], operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens[i]);
            }
        }

        // Process remaining operators
        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        // The result should be on top of the 'values' stack
        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        return true;
    }

    private static double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }
}
