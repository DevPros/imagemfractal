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
public class FractalThread extends Thread {

    int ini, fin;
    FractalImage frac;

    public FractalThread(int ini, int fin, FractalImage fractal) {
        this.ini = ini;
        this.fin = fin;
        this.frac = fractal;
    }

    @Override
    public void run() {
        for (int y = 0; y < frac.height; y++) {
            for (int x = 0; x < frac.width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY + (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));
                if (index == 256) {
                    frac.img.setRGB(x, y, new Color(0, 0, 0).getRGB());
                } else {
                    int color = Color.HSBtoRGB((float) index / 256, 1, 1);
                    frac.img.setRGB(x, y, color);
                }
                /*else {
                    int val = 255;
                    double red = index | (index << 2);
                    while (red >= val) { red-=val; }
                    double green = index | (index << 4);
                    while (green >= val) { green-=val; }
                    double blue = index | (index << 8);
                    while (blue >= val) { blue-=val; }
                    Color color = new Color((int) red, (int) green, (int) blue);
                    frac.img.setRGB(x, y, color.getRGB());
                }*/
            }
        }
    }
}
