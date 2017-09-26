package fractal;

import fractal.functions.FractInventado;
import vendor.Complex;
import fractal.functions.Fractal;
import fractal.functions.Mandelbrot;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.Dimension;

/**
 *
 * @author canoso
 */
public class FractalComponent extends JComponent {

    private BufferedImage img;
    private Fractal fractal;

    private int width;
    private int height;

    public FractalComponent() {
        this(800, 600);
    }

    public FractalComponent(int width, int height) {
        this.fractal = new Mandelbrot();
        resizeImg(width, height);
    }

    public void resizeImg(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height));
        
        img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        calculateFractal();
    }

    @Override
    public void paintComponent(Graphics gr) {
        gr.drawImage(img, 0, 0, null);
    }

    private void calculateFractal() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double reX = ((x - (width / 2)) * 4.0) / width;
                double reY = ((y - (height / 2)) * 4.0) / width;
                int index = fractal.getDivergentIteration(new Complex(reX, reY));
                if (index == fractal.getMaxIter()) {
                    img.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    img.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
    }
}
