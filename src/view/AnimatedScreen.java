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
        /*for(int i = 1; i < this.carsLeft + 1; i++) {
            g.setColor(Color.RED);
            g.fillRect(xLeft + 20 - (i * 110),30,60,20);
            g.fillRect(xLeft - (i * 110),50,100,30);
            g.setColor(Color.BLACK);
            g.fillRect(xLeft + 15 - (i * 110),70,17,17);
            g.fillRect( xLeft + 68 - (i * 110),70,17,17);
            g.drawString(String.valueOf(i),xLeft + 45 - (i * 110), 60);
        }

        for(int i = 1; i < this.carsRight + 1; i++) {
            g.setColor(Color.RED);
            g.fillRect(xRight + 20 + ((i - 1) * 110),30,60,20);
            g.fillRect(xRight + ((i - 1) * 110),50,100,30);
            g.setColor(Color.BLACK);
            g.fillRect(xRight + 15 + ((i - 1) * 110),70,17,17);
            g.fillRect( xRight + 68 + ((i - 1) * 110),70,17,17);
            g.drawString(String.valueOf(i),xRight + 45 + ((i - 1) * 110), 60);
        }

         */
        g.setColor(Color.WHITE);
        for(int i = 0; i < 10; i++) {
            g.fillRect(i + (i * 50), 95,35,10);
        }
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
            }
        }




    }
}
