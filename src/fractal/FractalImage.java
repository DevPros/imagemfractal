package fractal;

import threads.Parallel;
import fractal.functions.FractalFunction;
import fractal.functions.Madelbroth;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;
import threads.Balanced;

/**
 *
 * @author canoso
 */
public final class FractalImage extends JComponent implements MouseListener {

    public BufferedImage img;
    public FractalFunction fractal;

    FractalCalculus calculus;
    
    public int width;
    public int height;

    public int alg;

    public double centerX = 0;
    public double centerY = 0;
    public double zoom = 0;

    static float Saturation = 1f;
    static float Brightness = 1f;

    /**
     * Construtor por defeito 
     * Assim é possivel arrastar este elemento para a GUI
     */
    public FractalImage() {
        this(800, 600, new Madelbroth(), 1, Saturation, Brightness);
    }
    
    /**
     * Construtor com parametros de largura e altura
     *
     * @param width largura do fractal
     * @param height altura do fractal
     * @param f
     * @param alg
     * @param saturation
     * @param brightness
     */
    public FractalImage(int width, int height, FractalFunction f, int alg, float saturation, float brightness) {
        setFractalFunction(f);
        setAlg(alg);
        resizeImg(width, height);
        this.Saturation = saturation / 255f;
        this.Brightness = brightness / 255f;
        //frac = new TesteParalelo(width, height, img, fractal);
    }

    public float getSaturation() {
        return Saturation;
    }

    public void setSaturation(float Saturation) {
        this.Saturation = Saturation;
    }

    public float getBrightness() {
        return Brightness;
    }

    public void setBrightness(float Brightness) {
        this.Brightness = Brightness;
    }

    public int getAlg() {
        return alg;
    }

    public void setAlg(int alg) {
        this.alg = alg;
    }

    /**
     * Implementa dinamicamente o algoritmo de exploração dos fractais
     *
     * @param func - Algoritmo
     */
    public void setFractalFunction(FractalFunction func) {
        this.fractal = func;
    }

    public void resizeImg(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width, height));
        img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.addMouseListener(this);
        zoom = (4.00 / width) * 2;
        algo(getAlg());
    }
    public void setCalculateFractalGUI(JProgressBar pb, JTextComponent txt){
        calculus.calculate();
    }
    public void stopCalculateFractalGUI(){
        calculus.stop();
    }
    public void initCalculateFractalGUI(){
        
    }
    public void algo(int i) {
        switch (i) {
            case 0:
                calculateFractalSequential();
                break;
            case 1:
                calculateFractalParallel();
                break;
            case 2:
                calculateFractalBalanced();
                break;
            default:
                calculateFractalBalanced();
                break;
        }
    }

    @Override
    public void paintComponent(Graphics gr) {
        //gr.drawImage(img, 0, 0, null);
        gr.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

    private void calculateFractalSequential() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double reX = centerX + (x - width / 2) * zoom;
                double reY = centerY + (y - height / 2) * zoom;
                int index = fractal.getDivergentIteration(new Complex(reX, reY));

                float Hue = (index % 256) / 255.0f;
                //float Brightness = index < 256 ? 1f : 0;

                Color color = Color.getHSBColor(Hue, getSaturation(), getBrightness());
                img.setRGB(x, y, color.getRGB());
            }
        }
        repaint();
    }

    private void calculateFractalParallel() {

        // Array de threads com o nº de processadores
        int cores = Runtime.getRuntime().availableProcessors();
        Parallel[] thr = new Parallel[cores];

        // dimensao do intervalo
        int dim = this.height / cores;

        for (int i = 0; i < thr.length; i++) {
            // criar as threads com os limites
            thr[i] = new Parallel(dim * i, (i + 1) * dim, this, getSaturation(), getBrightness());
            // executar as threads
            thr[i].start();
        }

        // Esperar que todas as threads terminem
        for (int i = 0; i < thr.length; i++) {
            try {
                thr[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(FractalImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void calculateFractalBalanced() {

        // Array de threads com o nº de processadores
        int cores = Runtime.getRuntime().availableProcessors();
        Balanced[] thr = new Balanced[cores];

        // senhas para os termos dos intervalos
        //AtomicInteger ticket = new AtomicInteger(256); /599
        AtomicInteger ticket = new AtomicInteger(this.height - 1); //2160

        for (int i = 0; i < thr.length; i++) {
            // atribuir a cada thread um conjunto de iterações
            thr[i] = new Balanced(ticket, this, getSaturation(), getBrightness());
            // executar as threads
            thr[i].start();
        }

        // Esperar que as threads concluam o trabalho
        for (int i = 0; i < thr.length; i++) {
            try {
                thr[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(FractalImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        centerX = centerX + (e.getX() - width / 2) * zoom;
        centerY = centerY + (e.getY() - height / 2) * zoom;
        if (e.getButton() == MouseEvent.BUTTON1) {
            zoom *= 1.2;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            zoom /= 1.2;
        }
        algo(getAlg());
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
