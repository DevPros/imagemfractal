/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public abstract class FractalCalculus {

    public JProgressBar pb;
    public JTextComponent txt;
    public FractalImage frac;
    long time;

    /**
     * Construtor abstrato para receber a progressbar, textfield e o fractal
     *
     * @param pb
     * @param txt
     * @param frac
     */
    public FractalCalculus(JProgressBar pb, JTextComponent txt, FractalImage frac) {
        this.pb = pb;
        this.txt = txt;
        this.frac = frac;
    }

    /**
     * Vai obrigar as classes que herdem desta a usar estes métodos
     */
    public abstract void calculate();
    /**
     * Vai obrigar as classes que herdem desta a usar estes métodos
     */
    public abstract void stop();

    /**
     * Retorna o tempo em milisegundos
     *
     * @return long
     */
    public long getTime() {
        return time;
    }

    /**
     * Retorna o tempo para humanos (Horas:Minutos:Segundos:Ms)
     *
     * @return String
     */
    public String getTimeHum() {
        return String.format("%d:%d:%d:%d",
                TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) % 60,
                TimeUnit.MILLISECONDS.toSeconds(time) % 60,
                TimeUnit.MILLISECONDS.toMillis(time) % 1000
        );
    }
}
