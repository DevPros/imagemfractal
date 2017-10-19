/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.Complex;
import fractal.FractalImage;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Canoso
 */
public class Sequential extends FractalCalculus implements Runnable{
    
    Thread singleThread;

    public Sequential(JProgressBar pb, JTextComponent txt, FractalImage img) {
        super(pb, txt, img);
    }

    @Override
    public void calculate() {
        if (singleThread != null && singleThread.isAlive()){
            stop();
        }
        singleThread = new Thread(this);
        singleThread.start();
    }

    @Override
    public void stop() {
        if (singleThread.isAlive()){
            singleThread.interrupt();
        }
    }

    @Override
    public void run() {
        for (int y = 0; y < frac.height; y++) {
            for (int x = 0; x < frac.width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY + (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));

                float Hue = (index % 256) / 255.0f;
                //float Brightness = index < 256 ? 1f : 0;

                Color color = Color.getHSBColor(Hue, 1, 1);
                frac.img.setRGB(x, y, color.getRGB());
            }
        }
    }
    
}
