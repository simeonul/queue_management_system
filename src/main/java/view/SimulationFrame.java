package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SimulationFrame {

    private JFrame frame;
    private JTextField clientsTextField;
    private JTextField queueTextField;
    private JTextField minArrivalTextField;
    private JTextField minProcessingTextField;
    private JTextField maxProcessingTextField;
    private JTextField timeTextField;
    private JLabel titleLabel;
    private JLabel clientsLabel;
    private JLabel queueLabel;
    private JLabel minArrivalLabel;
    private JLabel maxArrivalLabel;
    private JTextField maxArrivalTextField;
    private JLabel minProcessingLabel;
    private JLabel maxProcessingLabel;
    private JLabel timeLabel;
    private JButton startButton;
    private TextArea textArea;

    public SimulationFrame() {
        initialize();
    }
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        titleLabel = new JLabel("Queue Management System");
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 34));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(400, 20, 400, 50);
        frame.getContentPane().add(titleLabel);

        clientsTextField = new JTextField();
        clientsTextField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        clientsTextField.setBounds(310, 90, 150, 30);
        frame.getContentPane().add(clientsTextField);
        clientsTextField.setColumns(10);

        clientsLabel = new JLabel("Number of clients:");
        clientsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        clientsLabel.setBounds(50, 90, 200, 30);
        frame.getContentPane().add(clientsLabel);

        queueTextField = new JTextField();
        queueTextField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        queueTextField.setColumns(10);
        queueTextField.setBounds(990, 90, 150, 30);
        frame.getContentPane().add(queueTextField);

        queueLabel = new JLabel("Number of queues:");
        queueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        queueLabel.setBounds(730, 90, 200, 30);
        frame.getContentPane().add(queueLabel);

        minArrivalLabel = new JLabel("Min arrival time:");
        minArrivalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minArrivalLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        minArrivalLabel.setBounds(50, 140, 200, 30);
        frame.getContentPane().add(minArrivalLabel);

        minArrivalTextField = new JTextField();
        minArrivalTextField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        minArrivalTextField.setColumns(10);
        minArrivalTextField.setBounds(310, 140, 150, 30);
        frame.getContentPane().add(minArrivalTextField);

        maxArrivalLabel = new JLabel("Max arrival time:");
        maxArrivalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxArrivalLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        maxArrivalLabel.setBounds(730, 140, 200, 30);
        frame.getContentPane().add(maxArrivalLabel);

        maxArrivalTextField = new JTextField();
        maxArrivalTextField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        maxArrivalTextField.setColumns(10);
        maxArrivalTextField.setBounds(990, 140, 150, 30);
        frame.getContentPane().add(maxArrivalTextField);

        minProcessingLabel = new JLabel("Min processing:");
        minProcessingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minProcessingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        minProcessingLabel.setBounds(50, 190, 200, 30);
        frame.getContentPane().add(minProcessingLabel);

        minProcessingTextField = new JTextField();
        minProcessingTextField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        minProcessingTextField.setColumns(10);
        minProcessingTextField.setBounds(310, 190, 150, 30);
        frame.getContentPane().add(minProcessingTextField);

        maxProcessingLabel = new JLabel("Max processing:");
        maxProcessingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxProcessingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        maxProcessingLabel.setBounds(730, 190, 200, 30);
        frame.getContentPane().add(maxProcessingLabel);

        maxProcessingTextField = new JTextField();
        maxProcessingTextField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        maxProcessingTextField.setColumns(10);
        maxProcessingTextField.setBounds(990, 190, 150, 30);
        frame.getContentPane().add(maxProcessingTextField);

        timeLabel = new JLabel("Time limit:");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        timeLabel.setBounds(50, 240, 200, 30);
        frame.getContentPane().add(timeLabel);

        timeTextField = new JTextField();
        timeTextField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        timeTextField.setColumns(10);
        timeTextField.setBounds(310, 240, 150, 30);
        frame.getContentPane().add(timeTextField);

        startButton = new JButton("Start simulation");
        startButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        startButton.setBounds(730, 240, 410, 30);
        frame.getContentPane().add(startButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 300, 1150, 450);
        frame.getContentPane().add(scrollPane);

        textArea = new TextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        scrollPane.setViewportView(textArea);
    }
    public JFrame getFrame(){
        return this.frame;
    }
    public void newStartButtonListener(ActionListener l){
        this.startButton.addActionListener(l);
    }
    public int getClientsValue(){
        int val = Integer.parseInt(this.clientsTextField.getText());
        return val;
    }

    public int getQueuesValue(){
        int val = Integer.parseInt(this.queueTextField.getText());
        return val;
    }

    public int getMinArrivalValue(){
        int val = Integer.parseInt(this.minArrivalTextField.getText());
        return val;
    }

    public int getMaxArrivalValue(){
        int val = Integer.parseInt(this.maxArrivalTextField.getText());
        return val;
    }

    public int getMinProcessingValue(){
        int val = Integer.parseInt(this.minProcessingTextField.getText());
        return val;
    }

    public int getMaxProcessingValue(){
        int val = Integer.parseInt(this.maxProcessingTextField.getText());
        return val;
    }

    public int getTimeValue(){
        int val = Integer.parseInt(this.timeTextField.getText());
        return val;
    }

    public TextArea getTextArea() {
        return this.textArea;
    }

    public void showInputError(String error) {
        JOptionPane.showMessageDialog(this.frame, error);
    }
    public void resetValues(){
        this.clientsTextField.setText("");
        this.queueTextField.setText("");
        this.minArrivalTextField.setText("");
        this.minProcessingTextField.setText("");
        this.maxProcessingTextField.setText("");
        this.timeTextField.setText("");
        this.maxArrivalTextField.setText("");
    }
}
