/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.functions;

import fractal.Complex;

/**
 *
 * @author Canoso
 */
public class julia extends Fratal{

    @Override
    public int getDivergentIteration(Complex c) {
        Complex y = new Complex(2,0);
        Complex z = new Complex(0,0);
        
        int itera = 0;
        while (z.distanceToOrigin() < 2 && itera < super.maxIter) {
            z = z.times(y).plus(c);
            itera++;
        }
        return itera;}
    
}
