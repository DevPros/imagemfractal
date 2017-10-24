/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import external.Complex;
import fractal.FractalImage;
import java.awt.Color;

/**
 * @author João Canoso  https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class FractalThread extends Thread {

    // inicio do intervalo
    int ini;
    // fim do intervalo
    int fin;
    FractalImage frac;
    public long time;

    /**
     * 
     * @param ini
     * @param fin
     * @param frac 
     */
    public FractalThread(int ini, int fin, FractalImage frac) {
        this.ini = ini;
        this.fin = fin;
        this.frac = frac;
    }

    @Override
    public void run() {
        time = System.currentTimeMillis();
        for (int y = ini; y < fin; y++) {
            for (int x = 0; x < frac.width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY + (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));
                float Hue = (index % 256) / 255.0f;
                Color color = Color.getHSBColor(Hue, frac.getSaturation(), frac.getBrightness());
                frac.img.setRGB(x, y, color.getRGB());
            }
            frac.repaint();
            frac.revalidate();
        }
        time = System.currentTimeMillis() - time;
    }

}
