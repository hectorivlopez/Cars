package view;

import model.App;
import model.Car;

import javax.swing.*;
import java.awt.*;


public class AnimatedScreen extends JPanel {
    public App app;
    public AnimatedScreen(App app) {
        this.app = app;
    }

    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        for(int i = 0; i < 4; i++) {
            g.fillRect((i * 100), 97,85,6);
        }
        for(int i = 6; i < 10; i++) {
            g.fillRect(15 + (i * 100), 97,85,6);
        }
        g.fillRect(385,0,6,200);
        g.fillRect(609,0,6,200);

        if(this.app.getCarsPassing().equals("left")) {
            synchronized (this.app.getMutexLeft()) {
                if(!this.app.getWay().isEmpty()) {
                    for(Car car : this.app.getWay()) {
                        g.setColor(Color.RED);
                        g.fillRect(car.x + 20, car.y + 30, 60, 20);
                        g.fillRect(car.x, car.y + 50, 100, 30);
                        g.setColor(Color.BLACK);
                        g.fillOval(car.x + 10, car.y + 68, 25, 25);
                        g.fillOval(car.x + 65, car.y + 68, 25, 25);
                        g.setColor(Color.GRAY);
                        g.fillOval(car.x + 15, car.y + 73, 15, 15);
                        g.fillOval(car.x + 70, car.y + 73, 15, 15);
                        g.drawString(String.valueOf(car.name), car.x + 45, car.y + 60);

                    }
                }
                if(!this.app.getRightWay().isEmpty()) {
                    for(Car car : this.app.getRightWay()) {
                        g.setColor(Color.RED);
                        g.fillRect(car.x + 20, car.y + 30, 60, 20);
                        g.fillRect(car.x, car.y + 50, 100, 30);
                        g.setColor(Color.BLACK);
                        g.fillOval(car.x + 10, car.y + 68, 25, 25);
                        g.fillOval(car.x + 65, car.y + 68, 25, 25);
                        g.setColor(Color.GRAY);
                        g.fillOval(car.x + 15, car.y + 73, 15, 15);
                        g.fillOval(car.x + 70, car.y + 73, 15, 15);
                        g.drawString(String.valueOf(car.name), car.x + 45, car.y + 60);

                    }
                }
            }

        }
        else {
            synchronized (this.app.getMutexRigth()) {
                if(!this.app.getWay().isEmpty()) {
                    for(Car car : this.app.getWay()) {
                        g.setColor(Color.RED);
                        g.fillRect(car.x + 20, car.y + 30, 60, 20);
                        g.fillRect(car.x, car.y + 50, 100, 30);
                        g.setColor(Color.BLACK);
                        g.fillOval(car.x + 10, car.y + 68, 25, 25);
                        g.fillOval(car.x + 65, car.y + 68, 25, 25);
                        g.setColor(Color.GRAY);
                        g.fillOval(car.x + 15, car.y + 73, 15, 15);
                        g.fillOval(car.x + 70, car.y + 73, 15, 15);
                        g.drawString(String.valueOf(car.name), car.x + 45, car.y + 60);

                    }
                }
                if(!this.app.getLeftWay().isEmpty()) {
                    for(Car car : this.app.getLeftWay()) {
                        g.setColor(Color.RED);
                        g.fillRect(car.x + 20, car.y + 30, 60, 20);
                        g.fillRect(car.x, car.y + 50, 100, 30);
                        g.setColor(Color.BLACK);
                        g.fillOval(car.x + 10, car.y + 68, 25, 25);
                        g.fillOval(car.x + 65, car.y + 68, 25, 25);
                        g.setColor(Color.GRAY);
                        g.fillOval(car.x + 15, car.y + 73, 15, 15);
                        g.fillOval(car.x + 70, car.y + 73, 15, 15);
                        g.drawString(String.valueOf(car.name), car.x + 45, car.y + 60);

                    }
                }
            }
        }




    }
}
