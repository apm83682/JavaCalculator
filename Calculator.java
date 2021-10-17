import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Toolkit;


class Calculator implements ActionListener{

    JFrame frame;
    JTextField textField;
    JPanel panel;
    JButton[] numberButton = new JButton[10];
    JButton[] funcButton = new JButton[9]; 
    JButton addButton, multiplyButton, subtractButton, divideButton;
    JButton decimalButton, equalsButton, clearButton, deleteButton, negativeButton;

    Font myFont = new Font("Agency FB", Font.BOLD, 30);
    Double num1, num2, result;
    char operator;

    
    Calculator(){
        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");


        frame = new JFrame("Calculator");
        frame.setSize(420,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setIconImage(icon);

        textField = new JTextField();
        textField.setBounds(25, 25, 350, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        /*FUNCTION BUTTONS*/
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalsButton = new JButton("=");
        clearButton = new JButton("CLR");
        deleteButton = new JButton("DEL");
        negativeButton = new JButton("(-)");

        funcButton[0] = addButton;
        funcButton[1] = subtractButton;
        funcButton[2] = multiplyButton;
        funcButton[3] = divideButton;
        funcButton[4] = equalsButton;
        funcButton[5] = decimalButton;
        funcButton[6] = clearButton;
        funcButton[7] = deleteButton;
        funcButton[8] = negativeButton;
 
        for(int i = 0; i<9; i++){
            funcButton[i].addActionListener(this);
            funcButton[i].setFocusable(false);
            funcButton[i].setFont(myFont);
        }

        /*NUMBER BUTTONS*/
        for(int i=0; i<10; i++){
            numberButton[i] = new JButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFocusable(false);
            numberButton[i].setFont(myFont);
        }

        panel = new JPanel();
        panel.setBounds(25, 100, 350, 300);
        panel.setLayout(new GridLayout(4,4,10,10));
        
        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(funcButton[3]);
        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(funcButton[2]);
        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(funcButton[1]);
        panel.add(funcButton[5]);
        panel.add(numberButton[0]);
        panel.add(funcButton[4]);
        panel.add(funcButton[0]);

        clearButton.setBounds(25, 410 , 80, 70);
        deleteButton.setBounds(115, 410 , 80, 70);
        negativeButton.setBounds(205, 410 , 80, 70);

        frame.add(clearButton);
        frame.add(deleteButton);
        frame.add(negativeButton);
        frame.add(textField);
        frame.add(panel);
        frame.setVisible(true);

    }

    public static void main(String[] args){
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        for(int i=0; i<10; i++){
            if(ae.getSource() == numberButton[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
                
            }
        }
        if(ae.getSource() == decimalButton){
            textField.setText(textField.getText().concat("."));
        }
        if(ae.getSource() == addButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if(ae.getSource() == subtractButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if(ae.getSource() == multiplyButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if(ae.getSource() == divideButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if(ae.getSource() == equalsButton){
            num2 = Double.parseDouble(textField.getText());
            switch(operator){
                case '+': result = num1 + num2; break; 
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': result = num1 / num2; break;
            }
            textField.setText(String.valueOf(result));
        }

        if(ae.getSource() == clearButton){
            textField.setText("");
        }
        if(ae.getSource() == deleteButton){
            String whatsOnScreenNow = textField.getText();
            textField.setText("");
            for(int i=0; i<whatsOnScreenNow.length() - 1; i++){
                textField.setText(textField.getText() + (whatsOnScreenNow.charAt(i)));
            }
        }

        if(ae.getSource() == negativeButton){

            textField.setText(Double.toString(Double.parseDouble(textField.getText())* - 1));
        }
    }

}   