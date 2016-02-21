package com.yesilyurt.amine;

import java.util.Stack;

/**
 * HW10_131044004
 *
 * Definition: keeps static final values which represent operands
 *
 * @author Amine Yesilyurt
 */
public class Operator extends Operand {

    static final int EOL = 0;
    static final int VALUE = 1;
    static final int OPENPAREN = 2;
    static final int CLOSEPAREN = 3;
    static final int MULT = 4;
    static final int DIV = 5;
    static final int PLUS = 6;
    static final int MINUS = 7;

    //Inner class
    public static class Priority {

        public int entryAttribute;
        public int topOfOperatorStack;

        /**
         * This constructor initialize Priority class with two argument
         *
         * @param entryAttribute
         * @param topOfOperatorStack
         */
        public Priority(int entryAttribute, int topOfOperatorStack) {
            this.entryAttribute = entryAttribute;
            this.topOfOperatorStack = topOfOperatorStack;
        }
    }

    /**
     * Initializing elements of array of Priority class
     */
    public static Priority[] PriorityChart = new Priority[]{
        new Priority(0, -1), // EOL
        new Priority(0, 0), // VALUE
        new Priority(100, 0), // OPEN PARENTHESES
        new Priority(0, 99), // CLOSE PARENTHESES
        new Priority(5, 6), // MULTIPLICATION
        new Priority(5, 6), // DIVISION
        new Priority(3, 4), // PLUS
        new Priority(3, 4) // MINUS
    };

    /**
     * Keeps all operators as a integer
     */
    public Stack<Integer> operatorStack;

    /**
     * Constructor, initialize operator stack
     */
    public Operator() {

        operatorStack = new Stack<Integer>();
        operatorStack.push(EOL);
    }
}
