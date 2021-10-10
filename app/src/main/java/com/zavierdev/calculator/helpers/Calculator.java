package com.zavierdev.calculator.helpers;

import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class Calculator {
    public final String[] LIST_SUPPORTED_OPERATOR = {"^", "+", "-", "×", "÷"};

    /**
     * Perform calculation from operation input
     *
     * @param operation
     * @return
     */
    public double performCalculation(String operation) {
        operation = operation.replace(",", "").replace("×", "*").replace("÷", "/");
        operation = fillLostBracket(operation);
        double resultVal = new ExpressionBuilder(operation).build().evaluate();
        return resultVal;
    }

    /**
     * Check operation value that contain all of supported operator
     *
     * @param value
     * @return
     */
    public boolean checkIsOperator(String value) {
        boolean isOperator = false;
        int i = 0;
        while (true) {
            String[] strArr = this.LIST_SUPPORTED_OPERATOR;
            if (i >= strArr.length) {
                return isOperator;
            }
            if (strArr[i].equals(value)) {
                isOperator = true;
            }
            i++;
        }
    }

    /**
     * Fill lost open and close bracket to operation
     *
     * @param operation
     * @return
     */
    private String fillLostBracket(String operation) {
        if (String.valueOf(operation.charAt(operation.length() - 1)).equals("(")) {
            operation = operation.substring(0, operation.length() - 1);
        }
        return fillOpenBracket(fillCloseBracket(operation));
    }

    /**
     * Fill close bracket to operation
     *
     * @param operation
     * @return
     */
    private String fillCloseBracket(String operation) {
        int openBracketAmounts = 0;
        int closeBracketAmounts = 0;
        for (int i = 0; i < operation.length(); i++) {
            if (String.valueOf(operation.charAt(i)).equals("(")) {
                openBracketAmounts++;
            }
        }
        for (int i2 = 0; i2 < operation.length(); i2++) {
            if (String.valueOf(operation.charAt(i2)).equals(")")) {
                closeBracketAmounts++;
            }
        }
        for (int i3 = 0; i3 < openBracketAmounts - closeBracketAmounts; i3++) {
            operation = operation + ")";
        }
        return operation;
    }

    /**
     * Fill open bracket to operation
     *
     * @param operation
     * @return
     */
    private String fillOpenBracket(String operation) {
        int openBracketAmounts = 0;
        int closeBracketAmounts = 0;
        for (int i = 0; i < operation.length(); i++) {
            if (String.valueOf(operation.charAt(i)).equals(")")) {
                closeBracketAmounts++;
            }
        }
        for (int i2 = 0; i2 < operation.length(); i2++) {
            if (String.valueOf(operation.charAt(i2)).equals("(")) {
                openBracketAmounts++;
            }
        }
        for (int i3 = 0; i3 < closeBracketAmounts - openBracketAmounts; i3++) {
            operation = "(" + operation;
        }
        return operation;
    }

    /**
     * Convert number input with three digit comas
     *
     * @param number
     * @return
     */
    private String addThousandsDelimiter(double number) {
        String[] array = Double.toString(number).split("\\.");
        String numString = array[0];

        String newString = "";
        for (int i = 0; i < numString.length(); i++) {
            if ((numString.length() - i - 1) % 3 == 0) {
                newString += Character.toString(numString.charAt(i)) + ",";
            } else {
                newString += Character.toString(numString.charAt(i));
            }
        }

        final boolean isLastCharacterComa = newString.charAt(newString.length() - 1) == ',';
        if (isLastCharacterComa) {
            newString = newString.substring(0, newString.length() - 1);
        }

        final boolean isDecimalValue = Double.parseDouble(array[1]) > 0;
        if (isDecimalValue) {
            newString += "." + array[1];
        }

        return newString;
    }

    /**
     * Get list of numbers from operation
     *
     * @param operation
     * @return
     */
    private ArrayList<String> getListNumberFromOperation(String operation) {
        ArrayList<String> numbers = new ArrayList();
        String numberString = "";

        for (char operationChar : operation.toCharArray()) {
            final boolean isDigit = Character.isDigit(operationChar) || operationChar == '.';
            final boolean isLastCharacter = operationChar == operation.charAt(operation.length() - 1);

            if (isDigit && isLastCharacter && !numberString.isEmpty()) {
                numberString += operationChar;
                numbers.add(numberString);
                numberString = "";
            } else if (isDigit) {
                numberString += operationChar;
            } else if (!isDigit && !numberString.isEmpty()) {
                numbers.add(numberString);
                numberString = "";
            }
        }

        return numbers;
    }
}
