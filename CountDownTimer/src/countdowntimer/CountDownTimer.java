/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countdowntimer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class CountDownTimer {

    private int minutes = 0;
    private int seconds = 0;
    
    private Timer timer;
    private JLabel timerLabel;
    private JButton startButton;
    private JTextField minutesField, secondsField;
    
    public CountDownTimer(){
        
        // Create the main JFrame
        JFrame frame = new JFrame("Count Down Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.getContentPane().setBackground(Color.white);
        frame.setLocationRelativeTo(null);
        
        // Create the content panel with BorderLayout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        
        // Create and configure the timer label
        timerLabel = new JLabel("Time remaining: 00:00");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setFont(new Font("Noto Serif", Font.BOLD, 20));
        timerLabel.setForeground(Color.red);
        
        // Create the input panel for timer values
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBackground(Color.white);
        
        // Create input fields and start button
        minutesField = new JTextField(2);
        secondsField = new JTextField(2);
        startButton = new JButton("Start Timer");
        startButton.setBackground(new Color(30, 144, 255));
        startButton.setForeground(Color.white);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        
        // Add action listener for the start button
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            
               startTimer();
            
            }
        });
        
        // Add components to input panel
        inputPanel.add(new JLabel("Minutes:"));
        inputPanel.add(minutesField);
        inputPanel.add(new JLabel("Seconds:"));
        inputPanel.add(secondsField);
        inputPanel.add(startButton);
        
        // Add components to content panel
        contentPanel.add(timerLabel, BorderLayout.CENTER);
        contentPanel.add(inputPanel, BorderLayout.SOUTH);
        
        // Add content panel to the main JFrame
        frame.add(contentPanel);
        frame.setVisible(true);
                
    }
    
    private void startTimer(){
        // Stop the existing timer if it's running
        if(timer != null && timer.isRunning())
        {
            timer.stop();
        }
        
        try
        {
            // Parse input values for minutes and seconds
          minutes = Integer.parseInt(minutesField.getText());
          seconds = Integer.parseInt(secondsField.getText());
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid minutes and seconds.");
            return;
        }
        
        int totalSeconds = minutes * 60 + seconds + 1;
        
        
        // Create a new timer with an ActionListener
        timer = new Timer(1000, new ActionListener() {
            int count = totalSeconds;
            @Override
            public void actionPerformed(ActionEvent e) {
                
                count--;
                
                if(count >= 0)
                {
                  int remainningMinutes = count/60;
                  int remainningSeconds = count%60;
                  timerLabel.setText(String.format("Time Remaining: %02d:%02d", remainningMinutes, remainningSeconds));
                }
                else
                {
                  timer.stop();
                  timerLabel.setText("Time's Up!");
                }
                
            }
        });
        
        timer.start();  // Start the timer
        
    }
    
    
    public static void main(String[] args){
        
        SwingUtilities.invokeLater(()-> new CountDownTimer());
        
    }
    
    
}
