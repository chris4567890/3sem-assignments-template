package org.app;

public class LambdaCalculatorV2 {

    NumberShowerInt addition = (a,b) ->a+b;

    NumberShowerInt subTraction =(a,b)->a-b;

    NumberShowerInt multiplication = (a,b) -> a*b;

    NumberShowerInt division = (a,b) -> a/b;

    NumberShowerInt modulus = (a,b) -> a%b;

    public static int operate(int a,int b, NumberShowerInt aO){
        return aO.display(a,b);
    }

}
