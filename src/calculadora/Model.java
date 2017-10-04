package calculadora;

import static java.lang.Math.sqrt;

public class Model {
    
    public double calculate(double number1, double number2, String operator){
        switch(operator){
        case "+": return (number1 + number2);
        case "-": return (number1 - number2);
        case "x": return (number1 * number2);
        case "/": 
            if(number2 == 0)
            {
                return 0;
            }
            return (number1 / number2);
        case "√":
            return sqrt(number1);
        }
        
        System.out.println("Operador desconhecido: " + operator);
        return 0;
    }
}
