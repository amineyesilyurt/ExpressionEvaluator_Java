package com.yesilyurt.amine;

/**
 * HW10_131044004
 *
 * Definition: Expression class extends Operator class It has override toString
 * method 
 * Evaluates the array of expression elements
 *
 * @author Amine Yesilyurt
 */
public class Expression extends Operator {

    private String expression = ""; //The Expression will  evaluate.

    /**
     * Default constructor
     */
    public Expression() {

    }

    @Override
    public String toString() {
        return expression;
    }

    //Inner class

    private static class ElementOfExpression {

        private int theType = EOL; //type of element,it can be operators( +/-*() )or operand(all integers)
        private int theValue = 0; //if element is operand,theValue keep an integer

        public ElementOfExpression() {
            this(EOL);
        }

        public ElementOfExpression(int _t) {
            this(_t, 0);
        }

        public ElementOfExpression(int _t, int _v) {
            theType = _t;
            theValue = _v;
        }

        public int getType() {
            return theType;
        }

        public int getValue() {
            return theValue;
        }

    }

    /**
     * Returns type of element of expression
     *
     * @param element is element of expression
     * @return type of element of expression
     */
    public ElementOfExpression getTypeOfElement(String element) {

        int theValue;

        if (element.equals("=")) {
            return new ElementOfExpression();
        }
        if (element.equals("/")) {
            return new ElementOfExpression(DIV);
        }
        if (element.equals("*")) {
            return new ElementOfExpression(MULT);
        }
        if (element.equals("(")) {
            return new ElementOfExpression(OPENPAREN);
        }
        if (element.equals(")")) {
            return new ElementOfExpression(CLOSEPAREN);
        }
        if (element.equals("+")) {
            return new ElementOfExpression(PLUS);
        }
        if (element.equals("-")) {
            return new ElementOfExpression(MINUS);
        }

        try {
            theValue = Integer.parseInt(element);
        } catch (NumberFormatException e) {
            System.err.println("Unrecognized expression element");
            return new ElementOfExpression();
        }

        return new ElementOfExpression(VALUE, theValue);
    }

    private void elementOperation(ElementOfExpression newElement) {

        int newType = newElement.getType();
        int topOperator;

        switch (newType) {
            case VALUE:
                operandStack.push(newElement.getValue());
                return;

            case CLOSEPAREN:
                while ((topOperator = operatorStack.peek()) != OPENPAREN && topOperator != EOL) {
                    arithmeticOperations(topOperator);
                }
                if (topOperator == OPENPAREN) {
                    operatorStack.pop();  // Delete opening parentheseis
                } else {
                    System.err.println("Missing open parentheses");
                }
                break;

            default:
                while (PriorityChart[newType].entryAttribute
                        <= PriorityChart[topOperator = operatorStack.peek()].topOfOperatorStack) {
                    arithmeticOperations(topOperator);
                }
                if (newType != EOL) {
                    operatorStack.push(newType);
                }
                break;
        }
    }

    private void arithmeticOperations(int topOperator) {

        if (topOperator == OPENPAREN) {
            System.err.println("Unbalanced parentheses");
            operatorStack.pop();
            return;
        }
        int rightOperand = operandStackPop();
        int leftOperand = operandStackPop();

        if (topOperator == PLUS) {
            operandStack.push(leftOperand + rightOperand);
        } else if (topOperator == MINUS) {
            operandStack.push(leftOperand - rightOperand);
        } else if (topOperator == MULT) {
            operandStack.push(leftOperand * rightOperand);
        } else if (topOperator == DIV) {

            if (rightOperand != 0) {
                operandStack.push(leftOperand / rightOperand);
            } else {
                System.err.println("Division by zero");
                operandStack.push(leftOperand);
            }
        }
        operatorStack.pop();
    }

    /**
     * Get element from user If element equals "=" calls toString Prints to
     * screen whole expression and it's result
     *
     * @param element element of expression
     */
    public void getElement(String element) {

        if (!element.equals("=")) {
            expression += element;
        }

        ElementOfExpression newElement = getTypeOfElement(element);
        elementOperation(newElement);

        if (element.equals("=")) {

            if (operandStack.isEmpty()) {
                System.err.println("Missing operand!");
            }

            int result = operandStack.pop();

            if (!operandStack.isEmpty()) {
                System.err.println("Warning: missing operators!");
            }

            System.out.printf("%s = %d \n", this.toString(), result);

        }

    }

}//END OF EXPRESSION CLASS
