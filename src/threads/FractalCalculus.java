/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.*;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public abstract class FractalCalculus {
    public JProgressBar pb;
    public JTextComponent txt;
    public FractalImage frac;
    long time;

    public FractalCalculus(JProgressBar pb, JTextComponent txt, FractalImage frac) {
        this.pb = pb;
        this.txt = txt;
        this.frac = frac;
    }
    
    public abstract void calculate();
    public abstract void stop();

    public long getTime() {
        return time;
    }
    
}
