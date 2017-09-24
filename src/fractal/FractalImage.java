package fractal;

import fractal.functions.Fratal;
import fractal.functions.madelbroth;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.Dimension;

/**
 *
 * @author canoso
 */
public class FractalImage extends JComponent implements MouseListener {

    private BufferedImage img;
    private Fratal fractal; //??

    private int width;
    private int height;

    private double reX; //?
    private double reY; //?
    private double centerX; //?
    private double centerY; //?
    private double zoom;

    public void resizeImg(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height)); //?
        img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        //FractalImage(width,height);
        calculateFractal();
    }

    @Override
    public void paintComponent(Graphics gr) {
        gr.drawImage(img, 0, 0, null);
        gr.setColor(Color.green);
        //gr.drawString("TESTE", width, height);

    }

    public void setFractalFunction(Fratal func) {
        this.fractal = func;
    }

    private void calculateFractal() {
        double zoom = 4.00 / width;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double reX = centerX + (x - width / 2) * zoom; // ??
                double reY = centerY - (y - height / 2) * zoom; //??
                int index = fractal.getDivergentIteration(new Complex(reX, reY));
                if (index == 256) {
                    img.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    img.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
    }

    public FractalImage() {
        this(800, 600);
    }

    public FractalImage(int width, int height) {
        setFractalFunction(new madelbroth());
        resizeImg(width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double reX = centerX + (e.getX() - width/2)*zoom;
        double reY = centerY + (e.getY() - height/2)*zoom;
        
        if (e.getButton() == MouseEvent.BUTTON1) {
            centerX = reX;
            centerY = reY;
            zoom = 2;
            calculateFractal();
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
