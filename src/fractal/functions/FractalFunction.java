/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.functions;

import external.Complex;

/**
 *
 * @author canoso
 */
public abstract class FractalFunction {
    long maxIter = 256; // default was 256
    
    public FractalFunction() {
    }
    
    public FractalFunction(long iter) {
        this.maxIter = iter;
    }
    
    public long getMaxIter() {
        return maxIter;
    }

    public abstract int getDivergentIteration(Complex c);
}
