package graphics;

import math.Vector3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private int width;
    private int height;
    private Color[][] pixels;
    private BufferedImage output;

    public Image(int x, int y) {
        this.width = x;
        this.height = y;
        this.pixels = new Color[x][y];
        this.initialize();
    }

    public void initialize() {
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                this.setPixel(w, h, new Color(0 ,0 ,0));
            }
        }
    }

    public void setPixel(int x, int y, Color c) {
        this.pixels[x][y] = c;
    }

    public Color getPixel(int x, int y) {
        return this.pixels[x][y];
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }

    public Image scaleImageDownSoft(float factor) {
        float sizeFactor = 1/factor;
        int newWidth = (int)((float)this.width * sizeFactor);
        int newHeight = (int)((float)this.height * sizeFactor);

        int amountPixelPerNewPixel = (int)Math.ceil(factor);
        Image newImage = new Image(newWidth, newHeight);

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                int old_x = (int)(factor*x);
                int old_y = (int)(factor*y);

                // get neighbour pixels
                Color averageCol = new Color(0,0,0);
                int pixelAmount = 0;

                for (int i = -(amountPixelPerNewPixel/2); i < amountPixelPerNewPixel/2; i++) {
                    for (int j = -(amountPixelPerNewPixel/2); j < amountPixelPerNewPixel/2; j++) {
                        // check if index is out of bounds
                        if (old_x+i > 0 && old_x+i < this.width && old_y+j > 0 && old_y+j < this.height) {
                            Color c = this.getPixel(old_x+i, old_y+i);
                            averageCol = new Color(averageCol.getRed()+c.getRed(),
                                    averageCol.getGreen()+c.getGreen(), averageCol.getBlue()+c.getBlue() );
                            pixelAmount += 1;
                        }
                    }
                }
                Color averageCol2 = new Color( averageCol.getRed()/pixelAmount,
                        averageCol.getGreen()/pixelAmount, averageCol.getBlue()/pixelAmount  );

                newImage.setPixel(x, y, averageCol2);
            }
        }

        return newImage;

    }

    public BufferedImage toBufferedImage() {
        output = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Color pixel = pixels[w][h];
                //Color col = new Color((int)(pixel.getRed()/255), (int)(pixel.getGreen()/255), (int)(pixel.getBlue()/255));
                output.setRGB(w, h, pixel.getRGB());
            }
        }

        return output;
    }

    public void save(String filename) {
        this.toBufferedImage();

        try{
            File f = new File(filename);
            ImageIO.write(this.output, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }


}
