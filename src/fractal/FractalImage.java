package fractal;

import fractal.functions.FractalFunction;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;
import threads.FractalCalculus;
import threads.*;

/**
 *
 * @author canoso
 */
public final class FractalImage extends JComponent implements MouseListener, MouseMotionListener {

    public BufferedImage img;
    public FractalFunction fractal;

    public FractalCalculus calculus;

    public int width;
    public int height;

    public double centerX = 0;
    public double centerY = 0;
    public double zoom = 0;
    public double newZoom = 0;
    static float Saturation = 1f;
    static float Brightness = 1f;

    /**
     * Construtor por defeito Assim é possivel arrastar este elemento para a GUI
     */
    public FractalImage() {
        this(800, 600);
    }

    /**
     * Construtor com parametros de largura e altura
     *
     * @param width largura do fractal
     * @param height altura do fractal
     */
    public FractalImage(int width, int height) {
        resizeImg(width, height);
    }

    public float getSaturation() {
        return Saturation;
    }

    public float getBrightness() {
        return Brightness;
    }

    public void setSaturationBrightness(float Brightness, float Saturation) {
        this.Brightness = Brightness / 255f;
        this.Saturation = Saturation / 255f;
    }

    /**
     * Implementa dinamicamente o algoritmo de exploração dos fractais
     *
     * @param func - Algoritmo
     */
    public void setFractalFunction(FractalFunction func) {
        this.fractal = func;
    }

    /**
     * Altera a dimensão das imagens
     *
     * @param width
     * @param height
     */
    public void resizeImg(int width, int height) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.addMouseListener(this);
        zoom = (4.00 / width)*2;
    }

    public BufferedImage getImg() {
        return img;
    }
    
    public void setNewZoom(double newZoom) {
        this.newZoom = newZoom;
    }

    /**
     * Calcula sequencialmente o Fractal
     *
     * @param pb
     * @param txt
     */
    public void seqCalculateFractalGUI(JProgressBar pb, JTextComponent txt) {
        calculus = new Sequential(pb, txt, this);
    }

    /**
     * Calcula paralelamente o fractal
     *
     * @param pb
     * @param txt
     */
    public void parCalculateFractalGUI(JProgressBar pb, JTextComponent txt) {
        calculus = new Parallel(pb, txt, this);
    }

    /**
     * Calcula balaceamento o fractal
     *
     * @param pb
     * @param txt
     */
    public void balCalculateFractalGUI(JProgressBar pb, JTextComponent txt) {
        calculus = new Balanced(pb, txt, this);
    }

    /**
     * Inicia o calculo do Fractal
     */
    public void initCalculateFractalGUI() {
        calculus.calculate();
    }

    /**
     * Pára o calculo do Fractal
     */
    public void stopCalculateFractalGUI() {
        calculus.stop();
    }

    /**
     * Desenha o Fractal na tela
     *
     * @param gr
     */
    @Override
    public void paintComponent(Graphics gr) {
        //gr.drawImage(img, 0, 0, null);
        gr.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        centerX = centerX + (e.getX() - width / 2) * zoom;
        centerY = centerY + (e.getY() - height / 2) * zoom;
        if (e.getButton() == MouseEvent.BUTTON1) {
            zoom *= newZoom;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            zoom /= newZoom;
        }
        System.out.println(newZoom);
        initCalculateFractalGUI();
        repaint();
        revalidate();
    }

    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
