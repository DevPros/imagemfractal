/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import external.Complex;
import fractal.FractalImage;
import java.awt.Color;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JProgressBar;

/**
 *
 * @author Canoso
 */
public class FractalThreadBal extends Thread {

    FractalImage frac;
    public long time;
    JProgressBar pb;
    AtomicInteger ticket;
    int y;

    public FractalThreadBal(AtomicInteger ticket, FractalImage frac,JProgressBar pb) {
        this.ticket = ticket;
        this.frac = frac;
        this.pb = pb;
    }

    @Override
    public void run() {
        pb.setMaximum(y);
        
        time = System.currentTimeMillis();

        while ((y = ticket.getAndDecrement()) >= 0) {
            for (int x = 0; x < frac.width; x++) {
                double reX = frac.centerX + (x - frac.width / 2) * frac.zoom;
                double reY = frac.centerY + (y - frac.height / 2) * frac.zoom;
                int index = frac.fractal.getDivergentIteration(new Complex(reX, reY));

                float Hue = (index % 256) / 255.0f;
                //float Brightness = index < 256 ? 1f : 0;

                //int color = Color.HSBtoRGB((float)(index/256.0), 1, 1);
                //System.out.println("x:"+x +" "+ "y:"+y);
                Color color = Color.getHSBColor(Hue, frac.getSaturation(), frac.getBrightness());
                frac.img.setRGB(x, y, color.getRGB());
            }
            pb.setValue(y);
            frac.repaint();
            frac.revalidate();
        }
        time = System.currentTimeMillis() - time;
    }

}
