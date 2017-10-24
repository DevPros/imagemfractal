/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import external.Complex;
import fractal.*;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;

/**
 * @author João Canoso  https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Sequential extends FractalCalculus implements Runnable {

    public Thread singleThread;

    /**
     * Construtor sequencial
     *
     * @param pb
     * @param txt
     * @param frac
     */
    public Sequential(JProgressBar pb, JTextComponent txt, FractalImage frac) {
        super(pb, txt, frac);
    }

    /**
     * Inicia o calculo, chamando o .start() da thread
     */
    @Override
    public synchronized void calculate() {
        if (singleThread != null && singleThread.isAlive()) {
            stop();
        }
        singleThread = new Thread(this);
        txt.setText("A Calcular....");
        
        singleThread.start();
        
    }

    /**
     * Pára a thread
     */
    @Override
    public synchronized void stop() {
        if (singleThread.isAlive()) {
            singleThread.interrupt();
        }
    }

    /**
     * Efetua os calculos de forma sequencial
     */
    @Override
    public synchronized void run() {
        time = System.currentTimeMillis();
        pb.setMaximum(frac.height);
        for (int y = 0; y < frac.height; y++) {
            pb.setValue(y);
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
        txt.setText(getTimeHum());
    }

}
