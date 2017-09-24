package fractal;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author canoso
 */
public class APPFractal extends JPanel{
    
    FractalImage img = new FractalImage();
   
    public static void main(String[] args) {
        JFrame window = new JFrame("Fractal");
        
        // exit on close
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // center window
        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);

    }
}
