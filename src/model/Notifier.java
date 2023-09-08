package model;

import view.AnimatedScreen;

import java.util.LinkedList;

public class Notifier extends Thread {
    private App app;
    private AnimatedScreen screen;

    public Notifier(App app, AnimatedScreen screen) {
        this.app = app;
        this.screen = screen;
    }

    public void run() {
        while(true) {
            while(this.app.getCarsPassing().equals("left") ) {
                if(this.app.getWay().isEmpty()) {
                    if(!this.app.getCarsLeft().isEmpty()) {
                        synchronized (this.app.getMutexLeft()) {
                            this.app.getMutexLeft().notify();
                        }
                    }
                }
                else {
                    if(!this.app.getCarsLeft().isEmpty() && this.app.getWay().getLast().x >= 10) {
                        synchronized (this.app.getMutexLeft()) {
                            this.app.getMutexLeft().notify();
                        }
                    }
                }

                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            while(this.app.getCarsPassing().equals("right")) {
                if(this.app.getWay().isEmpty()) {
                    if(!this.app.getCarsRight().isEmpty()) {
                        synchronized (this.app.getMutexRigth()) {
                            this.app.getMutexRigth().notify();
                        }
                    }
                }
                else {
                    if(!this.app.getCarsRight().isEmpty() && this.app.getWay().getLast().x <= this.screen.getWidth() - 110) {
                        synchronized (app.getMutexRigth()) {
                            this.app.getMutexRigth().notify();
                        }
                    }
                }

                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
