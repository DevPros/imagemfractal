package fractal;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author canoso
 */
public class Main extends JPanel {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame window = new JFrame("Fractal");
                FractalComponent f = new FractalComponent(800, 600);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
                window.getContentPane().add(f);
                window.pack();
            }

        });
    }
}
