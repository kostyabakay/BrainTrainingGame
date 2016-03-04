package com.kostyabakay.braintraininggame.model;

import java.util.Random;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents expression for medium level.
 */
public class MediumExpression {
    private Random random = new Random();
    private int firstTerm = generateFirstTerm();
    private String firstOperator = generateOperator();
    private int secondTerm = generateSecondTerm();
    private String secondOperator = generateOperator();
    private int thirdTerm = generateThirdTerm();
    private int calculationResult = calculateExpression();

    /**
     * Generates first term of the expression.
     * @return random first term of the expression
     */
    private int generateFirstTerm() {
        return random.nextInt(10);
    }

    /**
     * Generates second term of the expression.
     * @return random second term of the expression
     */
    private int generateSecondTerm() {
        return random.nextInt(10);
    }

    /**
     * Generates third term of the expression.
     * @return random third term of the expression
     */
    private int generateThirdTerm() {
        return random.nextInt(10);
    }

    /**
     * Generates operator for this expression.
     * @return random generated operator
     */
    private String generateOperator() {
        String[] operators = {"+", "-", "*", "/"};
        int i = random.nextInt(3);
        return operators[i];
    }

    /**
     * Calculates result of generated expression.
     * @return result of generated expression
     */
    private int calculateExpression() {
        int firstTerm = getFirstTerm();
        String firstOperator = getFirstOperator();
        int secondTerm = getSecondTerm();
        String secondOperator = getSecondOperator();
        int thirdTerm = getThirdTerm();
        int result = 0;

        switch (firstOperator) {
            case "+":

                switch (secondOperator) {
                    case "+":
                        result = firstTerm + secondTerm + thirdTerm;
                        break;
                    case "-":
                        result = firstTerm + secondTerm - thirdTerm;
                        break;
                    case "*":
                        result = firstTerm + secondTerm * thirdTerm;
                        break;
                    case "/":
                        result = firstTerm + secondTerm / thirdTerm;
                        break;
                }

                break;

            case "-":

                switch (secondOperator) {
                    case "+":
                        result = firstTerm - secondTerm + thirdTerm;
                        break;
                    case "-":
                        result = firstTerm - secondTerm - thirdTerm;
                        break;
                    case "*":
                        result = firstTerm - secondTerm * thirdTerm;
                        break;
                    case "/":
                        result = firstTerm - secondTerm / thirdTerm;
                        break;
                }

                break;

            case "*":

                switch (secondOperator) {
                    case "+":
                        result = firstTerm * secondTerm + thirdTerm;
                        break;
                    case "-":
                        result = firstTerm * secondTerm - thirdTerm;
                        break;
                    case "*":
                        result = firstTerm * secondTerm * thirdTerm;
                        break;
                    case "/":
                        result = firstTerm * secondTerm / thirdTerm;
                        break;
                }

                break;

            case "/":

                switch (secondOperator) {
                    case "+":
                        result = firstTerm / secondTerm + thirdTerm;
                        break;
                    case "-":
                        result = firstTerm / secondTerm - thirdTerm;
                        break;
                    case "*":
                        result = firstTerm / secondTerm * thirdTerm;
                        break;
                    case "/":
                        result = firstTerm / secondTerm / thirdTerm;
                        break;
                }

                break;
        }

        return result;
    }

    public int getFirstTerm() {
        return firstTerm;
    }

    public int getSecondTerm() {
        return secondTerm;
    }

    public int getThirdTerm() {
        return thirdTerm;
    }

    public String getFirstOperator() {
        return firstOperator;
    }

    public String getSecondOperator() {
        return secondOperator;
    }

    public int getCalculationResult() {
        return calculationResult;
    }
}
