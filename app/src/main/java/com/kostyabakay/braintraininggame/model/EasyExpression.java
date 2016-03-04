package com.kostyabakay.braintraininggame.model;

import java.util.Random;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents expression for easy level.
 */
public class EasyExpression {
    private Random random = new Random();
    private int firstTerm = generateFirstTerm();
    private String operator = generateOperator();
    private int secondTerm = generateSecondTerm();
    private int calculationResult = calculateExpression();

    private int generateFirstTerm() {
        return random.nextInt(10);
    }

    private int generateSecondTerm() {
        return random.nextInt(10);
    }

    private String generateOperator() {
        String[] operators = {"+", "-", "*", "/"};
        int i = random.nextInt(3);
        return operators[i];
    }

    private int calculateExpression() {
        int firstTerm = getFirstTerm();
        String operator = getOperator();
        int secondTerm = getSecondTerm();
        int result = 0;

        switch (operator) {
            case "+":
                result = firstTerm + secondTerm;
                break;
            case "-":
                result = firstTerm - secondTerm;
                break;
            case "*":
                result = firstTerm * secondTerm;
                break;
            case "/":
                result = firstTerm / secondTerm;
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

    public String getOperator() {
        return operator;
    }

    public int getCalculationResult() {
        return calculationResult;
    }
}
