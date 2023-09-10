package view;

import controller.WindowController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Window extends JFrame {
    public JButton addCarLeftBtn;
    public JButton addCarRightBtn;
    public JLabel carsLeftLabel;
    public JLabel carsRightLabel;
    public AnimatedScreen screen;
    public JFrame frame;
    public JButton startBtn;

    public Window() {

        setSize(1000,500);
        setLocationRelativeTo(null);
        setLayout(null);


        setLayout(null);

        initComponents();
        setVisible(true);
    }

    public void initComponents() {
        carsLeftLabel = new JLabel();
        carsLeftLabel.setBounds(20,20,200,30);
        carsLeftLabel.setText("Izquierda: 0" );
        this.add(carsLeftLabel);

        carsRightLabel = new JLabel();
        carsRightLabel.setBounds(280,20,200,30);
        carsRightLabel.setText("Derecha: 0" );
        this.add(carsRightLabel);


        screen = new AnimatedScreen(WindowController.app);
        screen.setBackground(Color.DARK_GRAY);
        screen.setBounds(0,100,this.getWidth(),200);
        this.add(screen);


        addCarLeftBtn = new JButton();
        addCarLeftBtn.setText("Agregar carro");
        addCarLeftBtn.addActionListener(e -> addCarLeft(e));
        addCarLeftBtn.setBounds(25, 350,200,30);
        this.add(addCarLeftBtn);

        addCarRightBtn = new JButton();
        addCarRightBtn.setText("Agregar carro");
        addCarRightBtn.addActionListener(e -> addCarRight(e));
        addCarRightBtn.setBounds(275, 350,200,30);
        this.add(addCarRightBtn);



        startBtn = new JButton();
        startBtn.setBounds(150,400,200,30);
        startBtn.setText("Comenzar");

        startBtn.addActionListener(e -> start(e));
        this.add(startBtn);

    }

    public void start(ActionEvent e) {
        WindowController.startAnimation();
    }

    public void addCarLeft(ActionEvent e) {
        WindowController.addCarLeft();
    }

    public void addCarRight(ActionEvent e) {
        WindowController.addCarRight();
    }
}
