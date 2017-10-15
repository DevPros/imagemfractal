/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

import java.util.ArrayList;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * Executa um calculo que requere muito tempo de calculo
 * @author Convite
 */
public class BigCalculus extends SwingWorker<Double, Integer> {
    /**
     * Barra de Progresso do calculo
     */
    JProgressBar progressBar;

    public BigCalculus(JProgressBar sc) {
        this.progressBar = sc;
    }

    @Override
    protected Double doInBackground() throws Exception {
        progressBar.setVisible(true);
        for (int i = 0; i < 100; i++) {
            publish(i);
            Thread.sleep(100);
        }
        return new Double(0);
    }

    /**
     *
     * @param chunks
     */
    public void process(ArrayList<Integer> chunks) {
        progressBar.setValue(chunks.get(chunks.get(chunks.size() - 1)));
    }

    @Override
    public void done() {
        progressBar.setVisible(false);
    }

}
