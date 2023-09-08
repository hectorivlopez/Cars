package model;

import view.AnimatedScreen;

public class Repaint extends Thread {
    private AnimatedScreen screen;

    public Repaint(AnimatedScreen screen) {
        this.screen = screen;
    }

    public void run() {
        while (true) {
            screen.repaint();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
