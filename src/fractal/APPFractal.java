package fractal;

import fractal.functions.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author canoso
 */
public class APPFractal extends JPanel {

    public static void main(String[] args) {

        JFrame window = new JFrame("Fractal");
        FractalImage f = new FractalImage(3840, 2160);
        f.setFractalFunction(new Lyapunov(1024));
        //FractalImage f = new FractalImage(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
        window.setVisible(true);
        window.getContentPane().add(f);
        window.pack();
        // centra a janela
        window.setLocationRelativeTo(null);

    }
    
    
}