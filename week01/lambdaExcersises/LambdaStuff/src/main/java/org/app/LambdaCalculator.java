package org.app;

public class LambdaCalculator {

    public NumberShowerInt multiplication = (number1, number2) -> {
        return number1*number2;
    };

    public NumberShowerInt addition = (number1, number2) -> {
        return number1+number2;
    };

    public NumberShowerInt subtraction = (number1, number2) -> {
        return number1-number2;
    };

    public NumberShowerInt division = (number1, number2) -> {
        return number1/number2;
    };

    public NumberShowerInt modolus = (number1, number2) -> {
        return number1%number2;
    };

    public NumberShowerInt powerPower = (number1, number2) ->{
      return number1^number2;
    };


}
