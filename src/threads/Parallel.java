/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.Complex;
import fractal.FractalImage;
import java.awt.Color;


/**
 *
 * @author Canoso
 */
public class Parallel extends Thread{
    int ini, fin;
    FractalImage frac;
    // teste
    float Brightness = 1f;
    float Saturation = 1f;

    public Parallel(int ini, int fin, FractalImage fractal, float brightness, float saturation) {
        this.ini = ini;
        this.fin = fin;
        this.frac = fractal;
        this.Brightness = brightness;
        this.Saturation = saturation;
    }

    public float getBrightness() {
        return Brightness;
    }

    public void setBrightness(float Brightness) {
        this.Brightness = Brightness;
    }

    public float getSaturation() {
        return Saturation;
    }

    public void setSaturation(float Saturation) {
        this.Saturation = Saturation;
    }
    
    
    @Override
    public void run(){
        for (int y = 0; y < frac.height; y++) {
            for (int x = 0; x < frac.width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY + (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));
                float Hue = (index%256)/255.0f;
                //float Brightness = index < 256 ? 1f : 0;
                Color color = Color.getHSBColor(Hue, getSaturation(), getBrightness());
                frac.img.setRGB(x, y, color.getRGB());
            }
        }
    }
}
