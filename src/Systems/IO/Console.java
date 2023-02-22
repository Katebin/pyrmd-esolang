package systems.io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Console extends JFrame implements ActionListener {
    // console consts
    private final JPanel outputZone = new JPanel(new BorderLayout());
    private final JPanel inputZone = new JPanel(new BorderLayout());

    private final JTextArea outputTextBox = new JTextArea();
    private final JScrollPane outputScrollers = new JScrollPane(outputTextBox);
    private final JTextField inputTextBox = new JTextField();
    private final JButton inputSubmitButton = new JButton();

    // input handling
    private boolean submitPressed = false;
    private boolean awaitingInput = false;

    public Console() {
        // window setup
        this.setVisible(true);
        this.setSize(new Dimension(700, 325));
        this.setTitle("pyrmd");

        // panel setup
        this.setLayout(new BorderLayout());
        this.add(outputZone, BorderLayout.CENTER);
        this.add(inputZone, BorderLayout.SOUTH);

        // output setup
        outputZone.add(outputScrollers, BorderLayout.CENTER);

        outputTextBox.setEditable(false);
        outputScrollers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputScrollers.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // input setup
        inputZone.add(inputTextBox, BorderLayout.CENTER);
        inputZone.add(inputSubmitButton, BorderLayout.EAST);

        inputSubmitButton.setText("Submit");
        inputSubmitButton.addActionListener(this); // get event from frame

        // exit handling
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // handle input submission
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == inputSubmitButton & awaitingInput == true) { // ensure the system will not preload inputs
            submitPressed = true; // handle the press
        }
    }

    public void println(String string) {
        // print to console
        outputTextBox.append("> " + string + "\n");
    }

    public String input() {
        // get console input, will wait for user input
        String userInput;
        awaitingInput = true;

        while(submitPressed != true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        userInput = inputTextBox.getText();
        outputTextBox.append(">> " + userInput + "\n"); // log input
        inputTextBox.setText(""); // reset

        submitPressed = false; // reset for next input
        awaitingInput = false; // reset

        return userInput;
    }
}
