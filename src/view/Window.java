package view;

import controller.WindowController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Window extends JFrame {
    public JPanel bgPanel;
    public JButton addCarLeftBtn;
    public JButton addCarRightBtn;
    public JLabel carsLeftLabel;
    public JLabel carsRightLabel;
    public AnimatedScreen screen;
    public JFrame frame;
    public JButton startBtn;

    public Window() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        final JRootPane rootPane = this.getRootPane();
        rootPane.putClientProperty("apple.awt.fullWindowContent", true);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", true);

        setSize(1000,400);
        setLocationRelativeTo(null);
        setLayout(null);

        initComponents();

        setVisible(true);
    }

    public void initComponents() {
        bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0,0,this.getWidth(), this.getHeight());
        bgPanel.setBackground(new Color(15,17,26));
        setContentPane(bgPanel);

        carsLeftLabel = new JLabel();
        carsLeftLabel.setBounds(20,20,200,30);
        carsLeftLabel.setText("Izquierda: 0" );
        //this.add(carsLeftLabel);

        carsRightLabel = new JLabel();
        carsRightLabel.setBounds(280,20,200,30);
        carsRightLabel.setText("Derecha: 0" );
        //this.add(carsRightLabel);


        screen = new AnimatedScreen(WindowController.app);
        screen.setBackground(new Color(30,30,30));
        screen.setBounds(0,100,this.getWidth(),200);
        bgPanel.add(screen);


        addCarLeftBtn = new JButton();
        addCarLeftBtn.setBounds(100, 335,200,30);
        addCarLeftBtn.setBackground(new Color(54,54,54));
        addCarLeftBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        addCarLeftBtn.setFocusPainted(false);
        addCarLeftBtn.setFont(new Font("Arial", Font.PLAIN, 15));
        addCarLeftBtn.setForeground(Color.WHITE);
        addCarLeftBtn.setHorizontalAlignment(SwingConstants.CENTER);
        addCarLeftBtn.setText("Agregar carro");
        addCarLeftBtn.addActionListener(e -> {
            try {
                addCarLeft(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bgPanel.add(addCarLeftBtn);

        addCarRightBtn = new JButton();
        addCarRightBtn.setBounds(700, 335,200,30);
        addCarRightBtn.setBackground(new Color(54,54,54));
        addCarRightBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        addCarRightBtn.setFocusPainted(false);
        addCarRightBtn.setFont(new Font("Arial", Font.PLAIN, 15));
        addCarRightBtn.setForeground(Color.WHITE);
        addCarRightBtn.setHorizontalAlignment(SwingConstants.CENTER);
        addCarRightBtn.setText("Agregar carro");
        addCarRightBtn.addActionListener(e -> {
            try {
                addCarRight(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bgPanel.add(addCarRightBtn);



        startBtn = new JButton();
        startBtn.setBounds(400,335,200,30);
        startBtn.setBackground(new Color(54,54,54));
        startBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        startBtn.setFocusPainted(false);
        startBtn.setFont(new Font("Arial", Font.PLAIN, 15));
        startBtn.setForeground(Color.WHITE);
        startBtn.setHorizontalAlignment(SwingConstants.CENTER);
        startBtn.setText("Comenzar");
        startBtn.addActionListener(e -> start(e));
        bgPanel.add(startBtn);

    }

    public void start(ActionEvent e) {
        WindowController.startAnimation();
    }

    public void addCarLeft(ActionEvent e) throws IOException {
        WindowController.addCarLeft();
    }

    public void addCarRight(ActionEvent e) throws IOException {
        WindowController.addCarRight();
    }
}
