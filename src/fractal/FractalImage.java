package fractal;

import fractal.functions.FractalFunction;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;
import threads.FractalCalculus;
import threads.*;

/**
 *
 * @author canoso
 */
public final class FractalImage extends JComponent implements MouseListener {

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
        calculus = new FractalSequential(pb, txt, this);
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
     * Para o calculo do Fractal
     */
    public void stopCalculateFractalGUI() {
        calculus.stop();
    }

    /**
     * Desenha o Fractal na telaS
     *
     * @param gr
     */
    @Override
    public void paintComponent(Graphics gr) {
        //gr.drawImage(img, 0, 0, null);
        gr.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

    /*
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
            this.repaint();
        }
        
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
    } */
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
