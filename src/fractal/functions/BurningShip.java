/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.functions;

import external.Complex;

/**
 *
 * @author Canoso
 */
public class BurningShip extends FractalFunction {

    public BurningShip(long itera) {
        super(itera);
    }
    
    @Override
    public int getDivergentIteration(Complex c) {
        Complex z = new Complex(0,0);
        int itera = 0;
        while (z.distanceToOrigin() < 2 && itera < super.getMaxIter()) {
            Complex y = new Complex(Math.abs(z.re()), Math.abs(z.im()));
            z  = y.times(y).plus(c);
            itera++;
        }
        return itera;
    }
    

}
