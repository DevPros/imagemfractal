/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

import java.awt.Color;

/**
 *
 * @author Canoso
 */
public class FractalThread extends Thread{
    int ini, fin;
    FractalImage frac;

    public FractalThread(int ini, int fin, FractalImage fractal) {
        this.ini = ini;
        this.fin = fin;
        this.frac = fractal;
    }
    
    @Override
    public void run(){
        for (int y = 0; y < frac.height; y++) {
            for (int x = 0; x < frac.width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY - (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));
                if (index == 256) {
                    // White
                    Color color = new Color(0, 0, 0);
                    frac.img.setRGB(x, y, color.getRGB());
                } else {
                    double red = index | (index << 2);
                    while (red > 255) { red-=255; }
                    double green = index | (index << 4);
                    while (green > 255) { green-=255; }
                    double blue = index | (index << 8);
                    while (blue > 255) { blue-=255; }
                    
                    Color color = new Color((int) red, (int) green, (int) blue);
                    frac.img.setRGB(x, y, color.getRGB());
                }
            }
        }
    }
}
