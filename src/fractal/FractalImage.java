package fractal;

import fractal.functions.FractalFunction;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;
import threads.FractalCalculus;
import threads.*;

/**
 * @author João Canoso  https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public final class FractalImage extends JComponent {

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
     * Construtor por defeito 
     * Assim é possivel arrastar este elemento para a GUI
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
    
    /**
     * Retorna o valor da saturação
     * @return float
     */
    public float getSaturation() {
        return Saturation;
    }
    
    /**
     * Retorna o valor do brilho
     * @return float
     */
    public float getBrightness() {
        return Brightness;
    }
    
    /**
     * Define os valores do brilho e saturação
     * @param Brightness
     * @param Saturation 
     */
    public void setSaturationBrightness(float Brightness, float Saturation) {
        this.Brightness = Brightness / 255f;
        this.Saturation = Saturation / 255f;
    }

    /**
     * Implementa dinamicamente o algoritmo de exploração dos fractais
     * @param func - Algoritmo
     */
    public void setFractalFunction(FractalFunction func) {
        this.fractal = func;
    }

    /**
     * Altera a dimensão das imagens
     * @param width
     * @param height
     */
    public void resizeImg(int width, int height) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        zoom = (4.00 / width)*2;
    }
    
    /**
     * Returna a imagem to tipo BufferedImage
     * @return BufferedImage
     */
    public BufferedImage getImg() {
        return img;
    }
    
    /**
     * Define um novo zoom
     * @param newZoom 
     */
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
     * Calcula o fratal de forma balanciada
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
}
