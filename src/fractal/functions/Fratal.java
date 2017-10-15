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
    long maxIter = 256; // default was 256
    
    public Fratal() {
    }
    
    public Fratal(long iter) {
        this.maxIter = iter;
    }
    
    public long getMaxIter() {
        return maxIter;
    }

    public abstract int getDivergentIteration(Complex c);
}
