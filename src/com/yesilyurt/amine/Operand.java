package com.yesilyurt.amine;

import java.util.Stack;

/**
 * HW10_131044004
 *
 * Definition: keeps the integer operands only in the stack
 *
 * @author Amine Yesilyurt
 */
public class Operand {

    /**
     * Integer stack ,keeps operands only
     */
    public Stack<Integer> operandStack;

    /**
     * Default constructor
     */
    public Operand() {
        operandStack = new Stack<Integer>();
    }

    /**
     * Use pop() to get top of stack element
     *
     * @return an integer operand which top of stack
     */
    public int operandStackPop() {

        if (operandStack.isEmpty()) {
            System.err.println("Missing operand");
            return 0;
        }
        return operandStack.pop();
    }
}
