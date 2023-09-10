package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Car {
    public int name;
    public int x;
    public int y;
    public String side;
    public BufferedImage image;

    public Car(int name, String side, int x, int y) throws IOException {
        this.name = name;
        this.side = side;
        this.x = x;
        this.y = y;

        Random random = new Random();
        int imgNumber = random.nextInt(3 - 1 + 1) + 1;
        this.image = ImageIO.read(this.getClass().getResource("/view/img/car" + imgNumber + "-" + side + ".png"));;
    }
}
