/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.functions;

import external.Complex;

/**
 * @author João Canoso  https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class Madelbroth extends FractalFunction {
    
    public Madelbroth() {
    }
    
    public Madelbroth(long iter) {
        super(iter);
    }
    
    @Override
    public int getDivergentIteration(Complex c) {
        Complex z = new Complex(0,0);
        int itera = 0;
        while (z.distanceToOrigin() < 2 && itera < super.maxIter) {
            z = z.times(z).plus(c);
            itera++;
        }
        return itera;
    } 
}
