package fractal;

import fractal.functions.Fratal;
import fractal.functions.BurningShip;
import fractal.functions.madelbroth;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author canoso
 */
public class FractalImage extends JComponent implements MouseListener {

    BufferedImage img;
    Fratal fractal;

    int width;
    int height;

    double centerX = 0;
    double centerY = 0;
    double zoom = 0;

    public void resizeImg(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height));
        img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.addMouseListener(this);
        zoom = (4.00 / width) * 2;
        calculateFractal();
    }

    @Override
    public void paintComponent(Graphics gr) {
        gr.drawImage(img, 0, 0, null);
        
    }

    public void setFractalFunction(Fratal func) {
        this.fractal = func;
    }

    private void calculateFractal() {
        // Array de threads com o nº de processadores
        int cores = Runtime.getRuntime().availableProcessors();
        FractalThread[] thr = new FractalThread[cores];
        
        int dim = this.height / cores;
        
        for (int i = 0; i < cores; i++) {
            //thr[i] = new FractalThread(i+dim,(i+1)*dim, this);
            thr[i] = new FractalThread(dim * i,(i+1)*dim, this);
            thr[i].start();
        }
        
        for (int i = 0; i < cores; i++) {
            try {
                thr[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(FractalImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public FractalImage() {
        this(1920, 1080);
    }

    public FractalImage(int width, int height) {
        setFractalFunction(new BurningShip());
        resizeImg(width, height);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            centerX = centerX + (e.getX() - width / 2) * zoom;
            centerY = centerY - (e.getY() - height / 2) * zoom;
            zoom *= 1.2;
            calculateFractal();
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            centerX = centerX + (e.getX() - width / 2) * zoom;
            centerY = centerY - (e.getY() - height / 2) * zoom;
            zoom /= 1.2;
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
