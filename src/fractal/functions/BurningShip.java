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
public class BurningShip extends Fratal {

    public BurningShip(long itera) {
        super(itera);
    }
    
    public int getDivergentIteration(Complex c) {
        Complex z = new Complex(0,0);
        int itera = 0;
        while (z.distanceToOrigin() < 2 && itera < super.getMaxIter()) {
            z = z.absC().times(z.absC()).plus(c);
            itera++;
        }
        return itera;
    }
    

}
