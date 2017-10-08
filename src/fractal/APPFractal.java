package fractal;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author canoso
 */
public class APPFractal extends JPanel {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame window = new JFrame("Fractal");
                FractalImage f = new FractalImage(800, 600);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // center window
                window.setVisible(true);
                window.getContentPane().add(f);
                window.pack();
                window.setLocationRelativeTo(null);
            }

        });
    }
}
