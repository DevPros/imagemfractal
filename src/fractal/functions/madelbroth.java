/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.functions;

import fractal.complex;

/**
 *
 * @author canoso
 */
public class madelbroth extends fractalFunction {

    @Override
    public int getDivergentIteration(complex c) {
        complex z = new complex(0,0);
        int itera = 0;
        while (z.distanceToOrigin() < 2 && itera < 256) {
            z = z.times(z).plus(c);
            itera++;
        }
        return itera;
    }
    
}
