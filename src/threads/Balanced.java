/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import fractal.Complex;
import fractal.FractalImage;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JProgressBar;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Canoso
 */
public class Balanced extends FractalCalculus {

    AtomicInteger ticket;
    int y;
    FractalThread[] thr;
    public Balanced(JProgressBar pb, JTextComponent txt, FractalImage frac) {
        super(pb, txt, frac);
    }

    @Override
    public void calculate() {
        ticket = new AtomicInteger(frac.height - 1);
        while ((y = ticket.getAndDecrement()) >= 0) {

            for (int x = 0; x < frac.width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY + (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));

                float Hue = (index % 256) / 255.0f;
                //float Brightness = index < 256 ? 1f : 0;

                //int color = Color.HSBtoRGB((float)(index/256.0), 1, 1);
                //System.out.println("x:"+x +" "+ "y:"+y);
                Color color = Color.getHSBColor(Hue,  frac.getSaturation(), frac.getBrightness());
                frac.img.setRGB(x, y, color.getRGB());
            }
        frac.repaint();
        }
    }

    @Override
    public void stop() {
        if (thr != null){
            if (thr[0].isAlive()){
                for(FractalThread fractalThread : thr){
                    fractalThread.interrupt();
                }
            }
        }
    }
}
