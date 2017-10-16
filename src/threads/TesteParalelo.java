/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.Complex;
import fractal.FractalImage;
import fractal.functions.FractalFunction;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Canoso
 */
public class TesteParalelo /*extends FractalImage implements Runnable */{

    float Saturation = 1f;
    
    private int ini;
    private int fin;
    
    private int width;
    private int height;

    private double centerX = 0;
    private double centerY = 0;
    private double zoom = 0;
    
    BufferedImage img;
    private FractalFunction fractal;

    
    
    public TesteParalelo(int width, int height, BufferedImage img, FractalFunction fractal) {
        this.width = width;
        this.height = height;
        this.img = img;
        this.fractal = fractal;
    }
    
    public TesteParalelo(int ini, int fin, int width, int height, BufferedImage img, FractalFunction fractal) {
        this.ini = ini;
        this.fin = fin;
        this.width = width;
        this.height = height;
        this.img = img;
        this.fractal = fractal;
    }


    public void run() {
//    for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
        for (int y = ini; y < fin; y++) {
            for (int x = 0; x < width; x++) {
                double reX = centerX + (x - width / 2) * zoom;
                double reY = centerY + (y - height / 2) * zoom;
                int index = fractal.getDivergentIteration(new Complex(reX, reY));
                
                
                float Hue = (index%256)/255.0f;
                float Brightness = index < 256 ? 1f : 0;
                
                Color color = Color.getHSBColor(Hue, Saturation, Brightness);
                img.setRGB(x, y, color.getRGB());
            }
        }}

    public void calculate() {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        
        // Array de threads com o nº de processadores
        int cores = Runtime.getRuntime().availableProcessors();
        Thread[] thr = new Thread[cores];
        
        // dimensao do intervalo
        int dim = height / cores;
        
        for (int i = 0; i < thr.length; i++) {
            // criar as threads com os limites
            //thr[i] = new Thread(new TesteParalelo(dim * i, (i+1)*dim, width, height, img, fractal));
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
        
        System.out.println("Tempo com threads: " + (endTime - startTime) );
    
    }
    
}
