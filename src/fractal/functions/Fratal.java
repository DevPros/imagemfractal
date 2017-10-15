/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.functions;

import fractal.Complex;

/**
 *
 * @author canoso
 */
public abstract class Fratal {
    //long maxIter = 256; // default was 256
    private long maxIter = 256;

    public Fratal(long itera) {
        this.maxIter = itera;
    }

    public abstract int getDivergentIteration(Complex c);

    public void setMaxIter(long maxIter) {
        this.maxIter = maxIter;
    }

    public long getMaxIter() {
        return maxIter;
    }
}
