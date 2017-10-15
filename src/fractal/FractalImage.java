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
import threads.Balanced;
import threads.Sequential;
import threads.TesteParalelo;

/**
 *
 * @author canoso
 */
public class FractalImage extends JComponent implements MouseListener {

    public BufferedImage img;
    public FractalFunction fractal;
    

    public FractalImage frac;

    public int width;
    public int height;

    public double centerX = 0;
    public double centerY = 0;
    public double zoom = 0;
    
    float Saturation = 1f;
    /**
     * Construtor por defeito
     * Assim é possivel arrastar este elemento para a GUI
     */
    public FractalImage() {
        this(800, 600,new Madelbroth());
    }

    /**
     * Construtor com parametros de largura e altura
     * @param width largura do fractal
     * @param height altura do fractal
     */
    public FractalImage(int width, int height, FractalFunction f) {
        setFractalFunction(f);
        resizeImg(width, height);
        //frac = new TesteParalelo(width, height, img, fractal);
    }
    
    /**
     * Implementa dinamicamente o algoritmo de exploração dos fractais
     * @param func
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
        //calculateFractalSequential();
        calculateFractalParallel();
        //calculateFractalBalanced();
    }

    @Override
    public void paintComponent(Graphics gr) {
        //gr.drawImage(img, 0, 0, null);
        gr.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
    
    private void calculateFractalSequential(){
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY - (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));
                
                float Hue = (index%256)/255.0f;
                float Brightness = index < 256 ? 1f : 0;
                
                Color color = Color.getHSBColor(Hue, Saturation, Brightness);
                frac.img.setRGB(x, y, color.getRGB());
            }
        }
        
        endTime = System.currentTimeMillis();
        
        System.out.println("Tempo sequencial: " + (endTime - startTime) );
    }

    private void calculateFractalParallel() {
        
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        
        // Array de threads com o nº de processadores
        int cores = Runtime.getRuntime().availableProcessors();
        Parallel[] thr = new Parallel[cores];
        
        // dimensao do intervalo
        int dim = this.height / cores;
        
        for (int i = 0; i < thr.length; i++) {
            // criar as threads com os limites
            thr[i] = new Parallel(dim * i, (i+1)*dim, this);
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
        
        endTime = System.currentTimeMillis();
        
        System.out.println("Tempo Paralelo: " + (endTime - startTime) );
    }
    
    private void calculateFractalBalanced() {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
       
        // Array de threads com o nº de processadores
        int cores = Runtime.getRuntime().availableProcessors();
        Balanced[] thr = new Balanced[cores];
        
        // senhas para os termos dos intervalos
        //AtomicInteger ticket = new AtomicInteger(256); /599
        AtomicInteger ticket = new AtomicInteger(this.height-1); //2160
        
        for (int i = 0; i < thr.length; i++) {
            // atribuir a cada thread um conjunto de iterações
            thr[i] = new Balanced(ticket, this);
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
        
        endTime = System.currentTimeMillis();
        
        System.out.println("Tempo balanceado: " + (endTime - startTime) );
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            centerX = centerX + (e.getX() - width / 2) * zoom;
            centerY = centerY - (e.getY() - height / 2) * zoom;
            zoom *= 1.2;
            //calculateFractalParallel();
            //calculateFractalBalanced();
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            centerX = centerX + (e.getX() - width / 2) * zoom;
            centerY = centerY - (e.getY() - height / 2) * zoom;
            zoom /= 1.2;
            //calculateFractalParallel();
            //calculateFractalBalanced();
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
