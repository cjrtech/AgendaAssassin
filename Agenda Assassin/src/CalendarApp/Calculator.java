package CalendarApp;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[14];
    JButton ADD, SUBTRACTION, MULTIPLICATION, DIVISION, DECIMAL, EQUAL, DELETE, CLEAR, NEGATIVE, SIN, COS, TAN, EXP, ROOT;
    JPanel panel,panel2;
    Border border = BorderFactory.createLineBorder( Color.LIGHT_GRAY,5);
    Border border2 = BorderFactory.createLineBorder(new Color (250,250,250),3);
    Border border3 = BorderFactory.createLineBorder(new Color (44,135,44),1);

    Font myFont = new Font("Comic Sans MS", Font.BOLD, 40);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    String trig;


    Calculator() {

        frame = new JFrame("Calculator");
        frame.setSize(780, 600);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 670, 50);
        textfield.setFont(new Font("Comic Sans MS",Font.PLAIN,40));
        textfield.setBackground(new Color (44, 135, 44));
        textfield.setForeground(Color.white);
        textfield.setBorder(border2);
        textfield.setEditable(false);

        ADD = new JButton("+");
        SUBTRACTION = new JButton("-");
        MULTIPLICATION = new JButton("*");
        DIVISION = new JButton("/");
        DECIMAL = new JButton(".");
        EQUAL = new JButton("=");
        DELETE = new JButton("Del");
        CLEAR = new JButton("Clr");
        NEGATIVE = new JButton("(-)");
        SIN = new JButton("Sin");
        COS = new JButton("Cos");
        TAN = new JButton("Tan");
        EXP = new JButton("^");
        ROOT = new JButton("SR");

        functionButtons[0] = ADD;
        functionButtons[1] = SUBTRACTION;
        functionButtons[2] = MULTIPLICATION;
        functionButtons[3] = DIVISION;
        functionButtons[4] = DECIMAL;
        functionButtons[5] = EQUAL;
        functionButtons[6] = DELETE;
        functionButtons[7] = CLEAR;
        functionButtons[8] = NEGATIVE;
        functionButtons[9] = SIN;
        functionButtons[10] = COS;
        functionButtons[11] = TAN;
        functionButtons[12] = EXP;
        functionButtons[13] = ROOT;


        for (int i = 0; i < 14; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setForeground(Color.white);
            functionButtons[i].setBackground(new Color (44, 135, 44));
            functionButtons[i].setBorder(border3);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(new Color (44, 135, 44));
            numberButtons[i].setForeground(Color.white);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setBorder(border3);
            numberButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 670, 400);
        panel.setLayout(new GridLayout(4, 6, 2, 2));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(ADD);
        panel.add(SIN);
        panel.add(CLEAR);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(SUBTRACTION);
        panel.add(COS);
        panel.add(DELETE);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(MULTIPLICATION);
        panel.add(TAN);
        panel.add(ROOT);
        panel.add(DECIMAL);
        panel.add(numberButtons[0]);
        panel.add(NEGATIVE);
        panel.add(DIVISION);
        panel.add(EXP);
        panel.add(EQUAL);
        panel.setBackground(new Color (44, 135, 44));
        panel.setBorder(border);


        panel2 = new JPanel();
        panel2.setBounds(15, 500, 740, 10);

        frame.add(panel);
        frame.add(panel2);
        frame.add(textfield);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(163, 79, 10, 178));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == DECIMAL) {
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == ADD) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == SUBTRACTION) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == MULTIPLICATION) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (e.getSource() == DIVISION) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }
        if (e.getSource() == SIN) {
            num1 = Double.parseDouble(textfield.getText());
            trig = "Sin";
            result = Math.sin(num1);
            textfield.setText(String.valueOf(result));
        }
        if (e.getSource() == COS) {
            num1 = Double.parseDouble(textfield.getText());
            trig = "Cos";
            result = Math.cos(num1);
            textfield.setText(String.valueOf(result));
        }
        if (e.getSource() == TAN) {
            num1 = Double.parseDouble(textfield.getText());
            trig = "Tan";
            result = Math.tan(num1);
            textfield.setText(String.valueOf(result));
        }
        if (e.getSource() == EXP) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '^';
            textfield.setText("");
        }
        if (e.getSource() == ROOT) {
            num1 = Double.parseDouble(textfield.getText());
            trig = "S.R";
            result = Math.sqrt(num1);
            textfield.setText(String.valueOf(result));
        }

            if (e.getSource() == EQUAL) {
                num2 = Double.parseDouble(textfield.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    case '^':
                        result = Math.pow(num1,num2);
                        break;
                }
                textfield.setText(String.valueOf(result));
                num1 = result;
            }
            if (e.getSource() == CLEAR) {
                textfield.setText("");
            }
            if (e.getSource() == DELETE) {
                String string = textfield.getText();
                textfield.setText("");
                for (int i = 0; i < string.length() - 1; i++) {
                    textfield.setText(textfield.getText() + string.charAt(i));
                }
            }
            if (e.getSource() == NEGATIVE) {
                double temp = Double.parseDouble(textfield.getText());
                temp *= -1;
                textfield.setText(String.valueOf(temp));
            }
        }
    }