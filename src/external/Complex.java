package external;

/*
Copyright © 2000–2011, Robert Sedgewick and Kevin Wayne. 
Last updated: Tue Aug 30 09:58:33 EDT 2016.
 */
public class Complex {

    private final double re;   // the real part
    private final double im;   // the imaginary part

    // create a new object with the given real and imaginary parts
    /**
     * Construtor para receber os valoeres
     *
     * @param real
     * @param imag
     */
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    // return a new Complex object whose value is (this + b)
    /**
     * Calcula os valores recebidos e soma
     *
     * @param b
     * @return
     */
    public Complex plus(Complex b) {
        Complex a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    /**
     * Subtrai os valores recebidos no construtor
     *
     * @param b
     * @return
     */
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // return a new Complex object whose value is (this * b)
    /**
     * Multiplica os valores recebido nos complexos
     *
     * @param b
     * @return
     */
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // return the real or imaginary part
    /**
     * Mostra o número real do do complexo
     *
     * @return
     */
    public double re() {
        return re;
    }

    /**
     * Retorna o número imaginário da classe
     *
     * @return
     */
    public double im() {
        return im;
    }

    // a static version of plus
    /**
     * Multiplica os números complexos
     *
     * @param a
     * @param b
     * @return
     */
    public static Complex plus(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    /**
     * Calcula a hipotnusa
     *
     * @return
     */
    public double distanceToOrigin() {
        return Math.sqrt(re * re + im * im);
    }

}
