import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI {


    static String output;
    static String outputBad;
    static String outputGood = "Map is 20 x 20\n";
    static String input;
    static String instructions;
    static boolean isCorrect = false;
    static boolean inital = true;
    static boolean isActive = true;
    static boolean isRendered = false;
    static int tLength =0;
    static int tWidth=0;
    static int tX=-1;
    static int tY=-1;
    static int num =1;
    static Rectangle rec1;
    static Rectangle rec2;


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() { GUI.RunGUI(); }
        });
    }

    public static void RunGUI() {

        JFrame frame = new JFrame("Rectangle Alignment Tool - Mark Marcello"); // frame for the whole window
        JTextArea outputText = new JTextArea(); // holds and displays output
        JScrollPane scrollPane = new JScrollPane(outputText); // make output scrollable
        JPanel bottomPanel = new JPanel(); // panel to hold the bottom area
        JPanel inputPanel = new JPanel(); // panel to hold input text field and button
        JLabel instructionsText1 = new JLabel(); // displays instructions (line 1)
        JLabel instructionsText2 = new JLabel(); // displays instructions (line 2)
        JTextField inputText = new JTextField(30); // takes input text - submit text on enter or by pressing button
        JButton submitButton = new JButton("Run"); // submit the text to run

        // set configs
        frame.setSize(600, 500);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        outputText.setEditable(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        inputText.setMaximumSize(new Dimension(20, 1));

        // add children to parent elements
        bottomPanel.add(instructionsText1);
        //bottomPanel.add(instructionsText2);
        bottomPanel.add(inputPanel);
        inputPanel.add(inputText);
        inputPanel.add(submitButton);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        outputText.setText("Hello and welcome to the Rectangle Alignment Tool. \n" +
                "For the purpose of this demo you are given a 20x20 map and can add Rectangles to it.");

        instructionsText1.setText(setInstructions());


        // listeners - input text enter key & submit button
        inputText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    input = inputText.getText();

                    if (num < 3){
                        if(!isCorrect){
                            try {
                                addValues(Integer.parseInt(input));
                            } catch(NumberFormatException ne) {
                                output = "Please use a valid integer";
                            }
                        } else {
                            verifyRectangle(input);

                        }
                    } else {
                        // add render
                    }


                    System.out.println(input);
                    inputText.setText(""); // clear input text
                    // TODO: call function - set output text
                    instructionsText1.setText(setInstructions());
                    outputText.setText(output);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) { }
        });
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: read input & perform actions here
                input = inputText.getText();
                if (num < 3){
                    if(!isCorrect){
                        try {
                            addValues(Integer.parseInt(input));
                        } catch(NumberFormatException ne) {
                            output = "Please use a valid integer";
                        }
                    } else {
                        verifyRectangle(input);

                    }
                } else {
                    // add render
                }

                outputText.setText(output);
                instructionsText1.setText(setInstructions());
                inputText.setText(""); // clear input text
            }
        });
    }

    public static void addValues(int val){
        if (num < 3) {
            if (tLength == 0) {
                if (val > 0 && val < 20) {
                    tLength = val;
                    outputGood += "Rectangle " + num + " Length = " + tLength + "\n";
                    output = outputGood;
                } else {
                    outputBad = "Please use the correct size for the map (2-20)";
                    output = outputBad;
                }
            } else if (tWidth == 0) {
                if (val > 0 && val < 20) {
                    tWidth = val;
                    outputGood += "Rectangle " + num + " Width = " + tWidth + "\n";
                    output = outputGood;
                } else {
                    outputBad = "Please use the correct size for the map (2-20)";
                    output = outputBad;
                }
            } else if (tX == -1) {
                if (val < 0 || val + tWidth <= 20) {
                    tX = val;
                    outputGood += "Rectangle " + num + " Start X = " + tX + "\n";
                    output = outputGood;
                } else if (val < 0) {
                    outputBad = "Coordinate X can not start negative";
                    output = outputBad;
                } else if (val + tLength > 20) {
                    outputBad = "Invalid entry Start X at: " + val + " puts the rectangle out of bounds \n" +
                            (val + tWidth) + " is larger than the map Width of 20!";
                    output = outputBad;
                }
            } else if (tY == -1) {
                if (val < 0 || val + tLength <= 20) {
                    tY = val;
                    outputGood += "Rectangle " + num + " Start Y = " + tY + "\n";
                    output = outputGood;
                    isCorrect = true;
                } else if (val < 0) {
                    outputBad = "Coordinate Y can not start negative";
                    output = outputBad;
                } else if (val + tLength > 20) {
                    outputBad = "Invalid entry Start Y at: " + val + " puts the rectangle out of bounds \n" +
                            (val + tLength) + " is larger than the map Length of 20!";
                    output = outputBad;
                }
            }
        }
    }

    public static String setInstructions(){
        if (num < 3) {
            if (tLength == 0) {
                instructions = "please enter the length rectangle " + num + ".";

            } else if (tWidth == 0) {
                instructions = "please enter the Width rectangle " + num + ".";
            } else if (tX == -1) {
                instructions = "please enter the start X for rectangle " + num + ".";
            } else if (tY == -1) {
                instructions = "please enter the start Y for rectangle " + num + ".";
            } else {
                instructions = "Are these dimensions correct? (type y or n)";
            }
        }


        return instructions;
    }

    public static void verifyRectangle(String val){
        if (val.toLowerCase().contains("y")){
            if (rec1 == null){
                rec1 = new Rectangle(tLength,tWidth,tX,tY);
            } else {
                rec2 = new Rectangle(tLength,tWidth,tX,tY);
            }
            tLength = 0;
            tWidth = 0;
            tX = -1;
            tY = -1;

            output = "Rectangle " + num + " has been added.";
            num++;
            isCorrect = false;
        } else if (val.toLowerCase().contains("n")){
            tLength = 0;
            tWidth = 0;
            tX = -1;
            tY = -1;
            output = "Very well, please enter the new values for Rectangle " + num;
            outputGood = "Map is 20 x 20\n";
        } else {
            output = "Please use y or n";
        }
    }

}