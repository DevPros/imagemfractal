/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.*;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class FractalSequential extends FractalCalculus implements Runnable {

    Thread singleThread;

    /**
     * Construtor
     *
     * @param pb
     * @param txt
     * @param frac
     */
    public FractalSequential(JProgressBar pb, JTextComponent txt, FractalImage frac) {
        super(pb, txt, frac);
    }

    /**
     * Function que serve para fazer o calculo da thread
     */
    @Override
    public void calculate() {
        if (singleThread != null && singleThread.isAlive()) {
            
                stop();
            
        }
        singleThread = new Thread(this);
        singleThread.start();
    }

    /**
     * Para a thread
     */
    @Override
    public void stop() {
        if (singleThread.isAlive()) {
            singleThread.interrupt();
        }
    }

    /**
     * Arranca a thread
     */
    @Override
    public void run() {
        pb.setMaximum(frac.height);
        txt.setText("A Calcular....");
        time = System.currentTimeMillis();
        for (int y = 0; y < frac.height; y++) {
            pb.setValue(y);
            for (int x = 0; x < frac.width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY + (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));

                float Hue = (index % 256) / 255.0f;
                //float Brightness = index < 256 ? 1f : 0;
                Color color = Color.getHSBColor(Hue, frac.getSaturation(), frac.getBrightness());
                frac.img.setRGB(x, y, color.getRGB());
            }
            frac.repaint();
        }
        time = System.currentTimeMillis() - time;
        txt.setText(time + "");
    }

}
