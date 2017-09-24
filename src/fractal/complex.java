package fractal;

/*
Copyright © 2000–2011, Robert Sedgewick and Kevin Wayne. 
Last updated: Tue Aug 30 09:58:33 EDT 2016.
*/
import java.util.Objects;

public class complex {
    private final double re;   // the real part
    private final double im;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public complex(double real, double imag) {
        re = real;
        im = imag;
    }

    // return a string representation of the invoking Complex object
    @Override
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    // return abs/modulus/magnitude
    public double abs() {
        return Math.hypot(re, im);
    }

    // return angle/phase/argument, normalized to be between -pi and pi
    public double phase() {
        return Math.atan2(im, re);
    }

    // return a new Complex object whose value is (this + b)
    public complex plus(complex b) {
        complex a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new complex(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    public complex minus(complex b) {
        complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new complex(real, imag);
    }

    // return a new Complex object whose value is (this * b)
    public complex times(complex b) {
        complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new complex(real, imag);
    }

    // return a new object whose value is (this * alpha)
    public complex scale(double alpha) {
        return new complex(alpha * re, alpha * im);
    }

    // return a new Complex object whose value is the conjugate of this
    public complex conjugate() {
        return new complex(re, -im);
    }

    // return a new Complex object whose value is the reciprocal of this
    public complex reciprocal() {
        double scale = re*re + im*im;
        return new complex(re / scale, -im / scale);
    }

    // return the real or imaginary part
    public double re() { return re; }
    public double im() { return im; }

    // return a / b
    public complex divides(complex b) {
        complex a = this;
        return a.times(b.reciprocal());
    }

    // return a new Complex object whose value is the complex exponential of this
    public complex exp() {
        return new complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // return a new Complex object whose value is the complex sine of this
    public complex sin() {
        return new complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    // return a new Complex object whose value is the complex cosine of this
    public complex cos() {
        return new complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    // return a new Complex object whose value is the complex tangent of this
    public complex tan() {
        return sin().divides(cos());
    }
    


    // a static version of plus
    public static complex plus(complex a, complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        complex sum = new complex(real, imag);
        return sum;
    }

    // See Section 3.3.
    @Override
    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        complex that = (complex) x;
        return (this.re == that.re) && (this.im == that.im);
    }

    // See Section 3.3.
    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
    
    public double distanceToOrigin() {
        return Math.sqrt(re * re + im * im);
    }
}