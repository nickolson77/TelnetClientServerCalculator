package com.test.calc;

import org.mariuszgromada.math.mxparser.Expression;


public class CalculatorImpl {
    
    public static String calculate(String expression){
       System.out.println("calc run");
       /* Expression exp = new Expression (expression);
        if(!exp.checkSyntax()){
            return "Error - " + exp.getErrorMessage();
        }
        else{*/
            return "Answer - "/* + exp.calculate()*/;
        /*}*/
       
    }
}
