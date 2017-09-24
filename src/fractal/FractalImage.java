package fractal;

import fractal.functions.fractalFunction;
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
    private fractalFunction fractal; //??
    
    private int width;
    private int height;
    
    private double reX; //?
    private double reY; //?
    private double centerX; //?
    private double centerY; //?
    private double zoom;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void reciveImg(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.setPreferredSize(new Dimension(height, width)); //?
        img = new BufferedImage(this.width, this.height,BufferedImage.TYPE_INT_RGB);
    }
    
    @Override
    public void paintComponent (Graphics gr) {
        gr.setColor(Color.green);
        //gr.drawString("TESTE", width, height);
        gr.drawImage(img, 0, 0, null);
    }
    
    public void setFractalFunction(fractal func) {
        this.func = func;
    }
    
    private void calculateFractal () {
        double zoom = 4/width;
        for (int y = 0; y<height;y++){
            for (int x = 0; x<width;x++){
                double reX = (x - width (2) + zoom); // ??
                double reY = (y - height (2) + zoom); //??
                int index = fractal.getDivergentIteration(new complex(reX, reY));
                
                if (index == 256){
                    img.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    img.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
    }
    
    public FractalImage() {
        this(800,600);
    }
    
    public FractalImage(int height, int width) {
        setFractalFunction(new madelbroth());
        resizeImg(height, width);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double reX = centerX + (e.getX() - width (2)*zoom);
        double reY = centerY + (e.getY() - height (2)*zoom);
        
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
