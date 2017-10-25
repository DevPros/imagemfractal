/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.FractalImage;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Balanced extends FractalCalculus {

    AtomicInteger ticket;
    int y;
    FractalThread[] thr;
    /**
     * Serve para receber os valores da progressbar, textfield e o fractal a ser calculado
     * @param pb
     * @param txt
     * @param frac 
     */
    public Balanced(JProgressBar pb, JTextComponent txt, FractalImage frac) {
        super(pb, txt, frac);
    }

    /**
     * Pede para efectuar os calculos
     */
    @Override
    public void calculate() {
        if (thr != null && thr[0].isAlive()) {
            stop();
        }
        time = System.currentTimeMillis();
        txt.setText("A calcular...");
        // Array de threads com o nº de processadores
        int cores = Runtime.getRuntime().availableProcessors();
        FractalThreadBal[] thr = new FractalThreadBal[cores];

        // senhas para os termos dos intervalos
        ticket = new AtomicInteger(frac.height - 1);

        for (int i = 0; i < thr.length; i++) {
            // atribuir a cada thread um conjunto de iterações
            thr[i] = new FractalThreadBal(ticket, frac, pb);
            // executar as threads
            thr[i].start();
            frac.revalidate();
            frac.repaint();
        }

        // Esperar que as threads concluam o trabalho
        for (int i = 0; i < thr.length; i++) {
            try {
                thr[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(FractalImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        time = System.currentTimeMillis() - time;
        txt.setText(getTimeHum());

    }

    /**
     * Para a thread se ela existir ou for nula
     */
    @Override
    public void stop() {
        if (thr != null) {
            if (thr[0].isAlive()) {
                for (FractalThread fractalThread : thr) {
                    fractalThread.interrupt();
                }
            }
        }
    }
}
