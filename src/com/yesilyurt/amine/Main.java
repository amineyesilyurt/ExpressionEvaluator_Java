package com.yesilyurt.amine;

import java.util.Scanner;

/**
 * HW10_131044004 
 * Definition: Tests Expression class
 *
 * @author Amine Yesilyurt
 */
public class Main {

    /**
     *
     * @param args no argument
     */
    public static void main(String[] args) {

        String again;
        String element;
        Scanner scan = new Scanner(System.in);

        do {
            Expression expression = new Expression();
            do {

                System.out.println("Enter your expression element");
                element = scan.next();
                expression.getElement(element);

            } while (!element.equals("="));

            System.out.println("Do you want to test again(Y/N)");
            again = scan.next();

        } while (again.equals("y") || again.equals("Y"));

    }
}
