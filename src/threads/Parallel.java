/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.FractalImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Canoso
 */
public class Parallel extends FractalCalculus {

    FractalThread[] thr;

    float Brightness = 1f;
    float Saturation = 1f;

    public Parallel(JProgressBar pb, JTextComponent txt, FractalImage img) {
        super(pb, txt, img);
    }

    @Override
    public void calculate() {

        if (thr != null && thr[0].isAlive()) {
            stop();
        }
        txt.setText("A Calcular...");
        time = System.currentTimeMillis();
        // Array de threads com o nº de processadores
        int cores = Runtime.getRuntime().availableProcessors();
        thr = new FractalThread[cores];

        // dimensao do intervalo
        int dim = frac.height / cores;

        for (int i = 0; i < cores; i++) {
            // criar as threads com os limites
            thr[i] = new FractalThread(dim * i, (i + 1) * dim, frac);
            // executar as threads
            thr[i].start();
            frac.repaint();
            frac.revalidate();
        }

        for (FractalThread fractalThread : thr) {
            try {
                fractalThread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Parallel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        time = System.currentTimeMillis() - time;
        txt.setText(time + "");
    }

    @Override
    public void stop() {
        if (thr != null) {
            //if (thr[0].isAlive()){
            for (FractalThread fractalThread : thr) {
                fractalThread.interrupt();
            }
            //}
        }
    }
}
