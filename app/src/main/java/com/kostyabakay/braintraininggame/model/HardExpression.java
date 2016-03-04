package com.kostyabakay.braintraininggame.model;

import java.util.Random;

/**
 * Created by Kostya on 04.03.2016.
 * This class represents expression for hard level.
 */
public class HardExpression {
    private Random random = new Random();
    private int firstTerm = generateFirstTerm();
    private String firstOperator = generateOperator();
    private int secondTerm = generateSecondTerm();
    private String secondOperator = generateOperator();
    private int thirdTerm = generateThirdTerm();
    private String thirdOperator = generateOperator();
    private int fourthTerm = generateFourthTerm();
    private int calculationResult = calculateExpression();

    private int generateFirstTerm() {
        return random.nextInt(10);
    }

    private int generateSecondTerm() {
        return random.nextInt(10);
    }

    private int generateThirdTerm() {
        return random.nextInt(10);
    }

    private int generateFourthTerm() {
        return random.nextInt(10);
    }

    private String generateOperator() {
        String[] operators = {"+", "-", "*", "/"};
        int i = random.nextInt(3);
        return operators[i];
    }

    private int calculateExpression() {
        int firstTerm = getFirstTerm();
        String firstOperator = getFirstOperator();
        int secondTerm = getSecondTerm();
        String secondOperator = getSecondOperator();
        int thirdTerm = getThirdTerm();
        String thirdOperator = getThirdOperator();
        int fourthTerm = getFourthTerm();
        int result = 0;

        switch (firstOperator) {
            case "+":

                switch (secondOperator) {
                    case "+":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm + secondTerm + thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm + secondTerm + thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm + secondTerm + thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm + secondTerm + thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "-":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm + secondTerm - thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm + secondTerm - thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm + secondTerm - thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm + secondTerm - thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "*":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm + secondTerm * thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm + secondTerm * thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm + secondTerm * thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm + secondTerm * thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "/":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm + secondTerm / thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm + secondTerm / thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm + secondTerm / thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm + secondTerm / thirdTerm / fourthTerm;
                                break;
                        }

                        break;
                }

                break;

            case "-":

                switch (secondOperator) {
                    case "+":

                        switch (thirdOperator) {

                            case "+":
                                result = firstTerm - secondTerm + thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm - secondTerm + thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm - secondTerm + thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm - secondTerm + thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "-":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm - secondTerm - thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm - secondTerm - thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm - secondTerm - thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm - secondTerm - thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "*":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm - secondTerm * thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm - secondTerm * thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm - secondTerm * thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm - secondTerm * thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "/":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm - secondTerm / thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm - secondTerm / thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm - secondTerm / thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm - secondTerm / thirdTerm / fourthTerm;
                                break;
                        }

                        break;
                }

                break;

            case "*":

                switch (secondOperator) {
                    case "+":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm * secondTerm + thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm * secondTerm + thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm * secondTerm + thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm * secondTerm + thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "-":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm * secondTerm - thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm * secondTerm - thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm * secondTerm - thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm * secondTerm - thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "*":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm * secondTerm * thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm * secondTerm * thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm * secondTerm * thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm * secondTerm * thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "/":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm * secondTerm / thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm * secondTerm / thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm * secondTerm / thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm * secondTerm / thirdTerm / fourthTerm;
                                break;
                        }

                        break;
                }

                break;

            case "/":

                switch (secondOperator) {
                    case "+":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm / secondTerm + thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm / secondTerm + thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm / secondTerm + thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm / secondTerm + thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "-":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm / secondTerm - thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm / secondTerm - thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm / secondTerm - thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm / secondTerm - thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "*":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm / secondTerm * thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm / secondTerm * thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm / secondTerm * thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm / secondTerm * thirdTerm / fourthTerm;
                                break;
                        }

                        break;

                    case "/":

                        switch (thirdOperator) {
                            case "+":
                                result = firstTerm / secondTerm / thirdTerm + fourthTerm;
                                break;
                            case "-":
                                result = firstTerm / secondTerm / thirdTerm - fourthTerm;
                                break;
                            case "*":
                                result = firstTerm / secondTerm / thirdTerm * fourthTerm;
                                break;
                            case "/":
                                result = firstTerm / secondTerm / thirdTerm / fourthTerm;
                                break;
                        }

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

    public int getFourthTerm() {
        return fourthTerm;
    }

    public String getFirstOperator() {
        return firstOperator;
    }

    public String getSecondOperator() {
        return secondOperator;
    }

    public String getThirdOperator() {
        return thirdOperator;
    }

    public int getCalculationResult() {
        return calculationResult;
    }
}
