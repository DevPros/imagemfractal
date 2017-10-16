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
public class Lyapunov extends FractalFunction {

    public Lyapunov(long itera) {
        super(itera);
    }

    public Lyapunov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getDivergentIteration(Complex c) {
        double sum = 0;
        double r;
        double z = 0.5;
        int itera = 0;
        while (sum < super.getMaxIter()) {
            
            r = z;
            z = r * z * (1 - z);
            sum += Math.log(r*(1 - 2*z))/Math.log(2);
        }
        sum = sum/(double)(super.getMaxIter()-1);
        return (int) sum;
    }
    

}
