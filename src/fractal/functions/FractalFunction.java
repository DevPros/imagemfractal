/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal.functions;

import external.Complex;

/**
 * @author João Canoso https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public abstract class FractalFunction {

    // número máximo de iterações
    long maxIter = 256;

    /**
     * Este construtor não serve para nada
     */
    public FractalFunction() {

    }

    /**
     * Serve enviar o número de iterações para postriormente ser calculado
     *
     * @param iter
     */
    public FractalFunction(long iter) {
        this.maxIter = iter;
    }

    /**
     * Obtem o maximo número de Iterações
     *
     * @return
     */
    public long getMaxIter() {
        return maxIter;
    }

    /**
     * Serve para obrigar as classes que herdem deste usem este método
     *
     * @param c
     * @return
     */
    public abstract int getDivergentIteration(Complex c);
}
