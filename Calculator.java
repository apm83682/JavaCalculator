import javax.print.event.PrintEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class CalculatorGUI{

    JTextField display;
    JTextField errors;
    JButton equalsButton;
    char operator ;
    int result = 0 , num1 = 0, num2 = 0;
    boolean operatorWasInputLastBefore = false;
    boolean numberWasInputLast = false;
    boolean firstTime = true;
    
    
    CalculatorGUI(){
    

    JFrame frm = new JFrame("Calculator");
    frm.setVisible(true);
    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frm.setSize(250,250);
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints gcb = new GridBagConstraints();
    gcb.insets = new Insets(3,3,3,3);


    action actionListen = new action();


    JButton[] numbers = new JButton[10];
    int i = 9;
    int gridx = 0;
    int gridy = 0;
    for(JButton button : numbers){
        button =  new JButton(Integer.toString(i));
        gridx = ((gridx+1) % 4 );
        if(gridx ==0){
            gridx++;
        }

        if(gridx == 1){
            gridy++;
        }

        gcb.gridx = gridx;
        gcb.gridy = gridy;
        
        
        panel.add(button,gcb);
        button.addActionListener(actionListen);
        i--;
        if(i==0){
            gridx = 5; // MEGA BRAIN MOVE. the worst way to do this.
            gridy = 4;
        }  
    }

    gcb.gridx = 3;
    equalsButton = new JButton("=");
    panel.add(equalsButton,gcb);
    equalsButton.addActionListener(actionListen);

    gcb.gridx = 1;
    JButton minusButton = new JButton("-");
    panel.add(minusButton,gcb);
    minusButton.addActionListener(actionListen);

    JButton additionButton = new JButton("+");
    gcb.gridx = 4;
    gcb.gridy = 3;
    gcb.gridheight = 2;
    gcb.fill = GridBagConstraints.VERTICAL;
    panel.add(additionButton, gcb);
    additionButton.addActionListener(actionListen);

    JButton multiplyButton = new JButton("*");
    gcb.gridy = 2;
    gcb.gridheight = 1;
    gcb.fill = GridBagConstraints.NONE;
    panel.add(multiplyButton, gcb);
    multiplyButton.addActionListener(actionListen);

    JButton divisionButton = new JButton("/");
    gcb.gridy = 1;
    panel.add(divisionButton, gcb);
    divisionButton.addActionListener(actionListen);

    display = new JTextField(10);
    display.setEditable(false);
    gcb.gridx = 1;
    gcb.gridy =0;
    gcb.gridwidth = 4;
    gcb.fill = GridBagConstraints.HORIZONTAL;
    panel.add(display,gcb);


        
    errors = new JTextField(10);
    errors.setEditable(false);
    gcb.gridx = 1;
    gcb.gridy = 5;
    panel.add(errors, gcb);

    
    frm.add(panel);
    }
  
    


    class action implements ActionListener{
        public void actionPerformed(ActionEvent ae)
        {
            JButton src = (JButton) ae.getSource();
            String dText = display.getText();
            display.setText( dText + "" + src.getText());
            char lastTypedButton = src.getText().charAt(0);
            errors.setText("");
            
            if(src == equalsButton){
                evaluate();
                display.setText(Integer.toString(result));
                num1 = num2 = 0;
                firstTime = true;
                src.setText(toggleCAndEquals(src.getText()));
                
                
            }
            else if(!isNumeric(Character.toString(lastTypedButton))){
                operator = lastTypedButton;
                operatorWasInputLastBefore = true;
                display.setText("");
                if(operatorWasInputLastBefore && numberWasInputLast){
                    evaluate();
                    operatorWasInputLastBefore =false;
                    numberWasInputLast= false;
                    num1 = result;
                    }
                
                
                
            }
            else if(!display.getText().equals(""))
            {
                num2 = Integer.parseInt(display.getText());
                numberWasInputLast = true;
            }

        }

        void evaluate(){
            if(firstTime){
            findNum(operator);
            firstTime = false;
            }
            try{
            switch(operator){
                case '-' : result = num1-num2; System.out.println(num1 +" " + num2 + " " + result); break;
                case '+' : result = num1+num2; System.out.println(num1 +" " + num2 + " " + result); break;
                case '*' : result = num1*num2; System.out.println(num1 +" " + num2 + " " + result); break; //issue
                case '/' : result = num1/num2; System.out.println(num1 +" " + num2 + " " + result); break;
                default : break;
            }
        }
        catch(Exception ae)
        {
            errors.setText(ae.getMessage());
            num1 = num2 = result = 0;
        }
        }

        boolean isNumeric(String str){
            try{
                int trial = Integer.parseInt(str);

            }
            catch(NumberFormatException numexp){
                return false;
            }
            return true;
        }
        
        void findNum(char operator){ //THE ABSOLUTE WORST WAY TO DO THIS!
            
            switch (operator){
                case '+' : num1 = 0; break;
                case '-' : num1 = 2*num2; break;
                case '*' : num1 = 1; break;
                case '/' : num1 = num2*num2; break;

            }
        }

        String toggleCAndEquals(String lbl){
            if(lbl == "="){
                lbl = "C";
            }
            else lbl = "=";
            return lbl;
        }
    }

    
}



public class Calculator{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new CalculatorGUI();
            }
        });
    }
}