package calculadora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Text output;

    private String operator = "";
    private double number1 = 0.0;
    private double result;
    private Model model = new Model();
    private boolean start = true;
    private boolean number1Value = false;//Controla se a variável number1 já possui um valor atribuído
    private boolean dot = false;//Controla o ponto (vírgula) dos números
    private boolean number1Negative = false;//Controla de se o primeiro número é negativo

    @FXML
    private void processNumber(ActionEvent event) {
        //Impede que números sejam concatenados como resultado de uma operação
        if (start) {
            output.setText("");
            start = false;
        }

        String value = ((Button) event.getSource()).getText();
        output.setText(output.getText() + value);
        number1Value = true;
    }

    @FXML
    private void processOperator(ActionEvent event) {

        String value = ((Button) event.getSource()).getText();

        if (!"=".equals(value)) {
            //Não faz nada se clicar mais de uma vez nos operadores
            if (!operator.isEmpty()) {
                if (operator.equals("-") && number1Value == true) {
                    operator = value;
                    number1 = Double.parseDouble(output.getText());
                    output.setText("");
                    dot = false;
                }
                return;
            }
            
            //Trata o caso de uma operação começando com número negativo
            if (value.equals("-")) {
                if (number1Value == true) {
                    operator = value;
                    number1 = Double.parseDouble(output.getText());
                    output.setText("");
                    dot = false;
                    return;
                } else {
                    output.setText(output.getText() + value);
                    start = false;
                    operator = value;
                }
                return;
            }

            //Impede de começar clicando nos operadores de +, * e /
            if (!number1Value) {
                return;
            }

            operator = value;
            number1 = Double.parseDouble(output.getText());
            output.setText("");
            dot = false;

        } else {
            //Se clicar no igual sem ter uma operação não faz nada
            if (operator.isEmpty() || number1Value == false) {
                return;
            }

            if (operator.equals("√")) {
                result = model.calculate(number1, 0, operator);
            } else {
                result = model.calculate(number1, Double.parseDouble(output.getText()), operator);
            }

            output.setText(String.valueOf(result));
            start = true;
            operator = "";
            number1Value = false;
        }
    }

    @FXML
    private void processClean(ActionEvent event) {

        number1 = 0.0;
        operator = "";
        output.setText("");
        number1Value = false;
        dot = false;
    }

    //Este método controla o uso do ponto (vírgula) no números
    @FXML
    private void processDot(ActionEvent event) {

        //Impede que pontos (vírgula) sejam concatenados como resultado de uma operação
        if (start) {
            output.setText("");
            start = false;
        }

        //Impede que o ponto (vírgula) seja clicada mais de uma vez
        if (dot) {
            return;
        }

        //Impede que inicie um número clicando no ponto (vírgula)
        if (!number1Value) {
            return;
        }

        String value = ((Button) event.getSource()).getText();
        output.setText(output.getText() + value);
        dot = true;
    }
}
