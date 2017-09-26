/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.functions;

import vendor.Complex;

/**
 *
 * @author canoso
 */
public abstract class Fractal {
    long maxIter = 256;

    public Fractal() {
    }

    public Fractal(long iter) {
        this.maxIter = iter;
    }
    
    public long getMaxIter() {
        return maxIter;
    }
    
    public abstract int getDivergentIteration(Complex c);
}
