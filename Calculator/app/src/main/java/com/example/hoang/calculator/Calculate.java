package com.example.hoang.calculator;

import java.util.Stack;

public class Calculate {
    public static String evaluate(String expression) {
        expression += " ";
        while (true){
            int exit = 0;
            if (expression.contains("++")){
                expression = expression.replace("++","+");
                exit =1;
            }
            if (expression.contains("--")){
                expression = expression.replace("--","+");
                exit =1;
            }
            if (expression.contains("+-")){
                expression = expression.replace("+-","-");
                exit =1;
            }
            if (expression.contains("-+")){
                expression = expression.replace("-+","-");
                exit =1;
            }
            if (expression.contains("*+")){
                expression = expression.replace("*+","*");
                exit =1;
            }
            if (expression.contains("/+")){
                expression = expression.replace("/+","/");
                exit =1;
            }
            if (exit ==0) break;
        }

        if (expression.contains("-/")||expression.contains("+/")||expression.contains("+*")||expression.contains("-*")
          ||expression.contains("**")||expression.contains("//")||expression.contains("*/")||expression.contains("/*")){
            return("Lỗi cú pháp");
        }

        char[] tokens = expression.toCharArray();
        Stack<Double> values = new Stack<Double>();


        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i] == ' ')
                continue;


            else if (tokens[i] == '-' && ((i>0 && (tokens[i-1] == '*' || tokens[i-1] == '/')) || (i==0))                     ){
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9')||tokens[i]=='.'||tokens[i]=='-'))
                    sbuf.append(tokens[i++]);
                i -=1;
                values.push(Double.parseDouble(sbuf.toString()));
            }
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }


            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/') {

                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                ops.push(tokens[i]);
            }

            else if ((tokens[i] >= '0' && tokens[i] <= '9')||(tokens[i]=='.')) {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9')||tokens[i]=='.'))
                    sbuf.append(tokens[i++]);
                i -=1;
                values.push(Double.parseDouble(sbuf.toString()));
            }

        }

        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        return String.valueOf(values.pop());
    }

    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }


    public static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}