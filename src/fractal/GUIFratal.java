/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

import fractal.functions.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author João Canoso  https://github.com/jpcanoso
 * @author Rui Barcelos https://github.com/barcelosrui
 */
public class GUIFratal extends javax.swing.JFrame {

    ButtonGroup bf = new ButtonGroup();
    ButtonGroup ba = new ButtonGroup();
    ButtonGroup br = new ButtonGroup();
    FractalImage f = new FractalImage();

    private JFrame JFrame = new JFrame();
    Dimension original;

    //ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
    /**
     * Creates new form GUIFratal
     */
    public GUIFratal() {
        initComponents();
        selectFactal();
        sliders(sl_bri);
        sliders(sl_sat);
        f.resizeImg(Integer.parseInt(txt_width.getText() + ""), Integer.parseInt(txt_height.getText() + ""));
        f.setNewZoom(Double.parseDouble(txt_zoom.getText().replace(",", ".")));
        f.setFractalFunction(new Madelbroth(Long.parseLong(txt_itera.getText())));
        f.setSaturationBrightness((float) sl_bri.getValue(), (float) sl_sat.getValue());
        f.seqCalculateFractalGUI(pbar, txt_seq);
        f.initCalculateFractalGUI();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(f);
        jPanel2.setVisible(true);
        setExtendedState(GUIFratal.MAXIMIZED_BOTH);
        evt();

    }

    public void evt() {
        f.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f.centerX = 0;
                f.centerY = 0;
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    f.centerX = f.centerX + (evt.getX() - (f.width / 2.0)) * f.zoom;
                    f.centerY = f.centerY - (evt.getY() - (f.height / 2.0)) * f.zoom;
                    f.zoom *= f.newZoom;
                    f.initCalculateFractalGUI();

                }
                if (evt.getButton() == MouseEvent.BUTTON3) {
                    f.centerX = f.centerX + (evt.getX() - (f.width / 2.0)) * f.zoom;
                    f.centerY = f.centerY - (evt.getY() - (f.height / 2.0)) * f.zoom;
                    f.zoom /= f.newZoom;
                    f.initCalculateFractalGUI();
                }
            }
        });
        f.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                original = new Dimension(getContentPane().getWidth(), getContentPane().getHeight());
            }
        });
    }

    /**
     * Agrupa os RadioButtons e pré define as selecções
     */
    private void selectFactal() {
        bf.add(rb_madelbroth);
        bf.add(rb_burning);
        rb_madelbroth.setSelected(true);
        ba.add(rb_seq);
        ba.add(rb_par);
        ba.add(rb_bal);
        rb_seq.setSelected(true);
        br.add(rb_uhd);
        br.add(rb_fhd);
        br.add(rb_rhd);
        br.add(rb_sd);
        rb_uhd.setSelected(true);
    }

    /**
     * Slider
     */
    private void sliders(JSlider sl) {
        sl.setMinimum(0);
        sl.setMajorTickSpacing(0);
        sl.setMaximum(255);
        sl.setMajorTickSpacing(255);
        sl.setValue(255);
        sl.setPaintTicks(true);
        sl.setPaintLabels(true);
    }

    /**
     * Define o tipo de fractal 1º MadelBroth 2º BurningShip
     */
    private void defineFractal(long itera) {
        if (rb_madelbroth.isSelected()) {
            f.setFractalFunction(new Madelbroth(itera));
        }
        if (rb_burning.isSelected()) {
            f.setFractalFunction(new BurningShip(itera));
        }
        calFractal();
    }

    /**
     * Opções para calcular Fractal -Sequencial -Paralelo -Balanciado
     */
    private void calFractal() {
        if (rb_seq.isSelected()) {
            f.seqCalculateFractalGUI(pbar, txt_seq);
        }
        if (rb_par.isSelected()) {
            f.parCalculateFractalGUI(pbar, txt_par);
        }
        if (rb_bal.isSelected()) {
            f.balCalculateFractalGUI(pbar, txt_bal);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        bt_save = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txt_width = new javax.swing.JTextField();
        txt_height = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        rb_uhd = new javax.swing.JRadioButton();
        rb_fhd = new javax.swing.JRadioButton();
        rb_rhd = new javax.swing.JRadioButton();
        rb_sd = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        rb_madelbroth = new javax.swing.JRadioButton();
        rb_burning = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        rb_seq = new javax.swing.JRadioButton();
        rb_par = new javax.swing.JRadioButton();
        rb_bal = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        sl_bri = new javax.swing.JSlider();
        sl_sat = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        l_bri = new javax.swing.JLabel();
        l_sat = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bt_calc = new javax.swing.JButton();
        pbar = new javax.swing.JProgressBar();
        bt_stop = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_zoom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_itera = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bt_minus = new javax.swing.JButton();
        bt_plus = new javax.swing.JButton();
        btn_aceleracao = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_seq = new javax.swing.JTextField();
        txt_par = new javax.swing.JTextField();
        txt_bal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fractal Explorer By Barcelos & Canoso");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1328, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        bt_save.setText("Salvar Imagem");
        bt_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_saveActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tamanho"));

        txt_width.setText("3840");

        txt_height.setText("2160");

        jLabel1.setText("Width");

        jLabel2.setText("Height");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        rb_uhd.setText("UHD");
        rb_uhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_uhdActionPerformed(evt);
            }
        });

        rb_fhd.setText("FHD");
        rb_fhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_fhdActionPerformed(evt);
            }
        });

        rb_rhd.setText("RHD");
        rb_rhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_rhdActionPerformed(evt);
            }
        });

        rb_sd.setText("SD");
        rb_sd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_sdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_height, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(txt_width))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rb_uhd)
                        .addGap(18, 18, 18)
                        .addComponent(rb_fhd))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rb_rhd)
                        .addGap(18, 18, 18)
                        .addComponent(rb_sd)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb_uhd)
                            .addComponent(rb_fhd))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb_rhd)
                            .addComponent(rb_sd))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Fractal"));

        rb_madelbroth.setText("Madelbroth");

        rb_burning.setText("Burning Ship");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        rb_seq.setText("Sequencial");

        rb_par.setText("Paralelo");

        rb_bal.setText("Balanciado");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rb_madelbroth)
                    .addComponent(rb_burning))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rb_seq)
                    .addComponent(rb_par)
                    .addComponent(rb_bal))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rb_madelbroth)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb_burning))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rb_seq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb_par)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rb_bal)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        rb_madelbroth.getAccessibleContext().setAccessibleName("RButtonMadelbroth");
        rb_burning.getAccessibleContext().setAccessibleName("RButtonBurningShip");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Cor"));

        sl_bri.setToolTipText("");
        sl_bri.setName(""); // NOI18N
        sl_bri.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sl_briStateChanged(evt);
            }
        });

        sl_sat.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sl_satStateChanged(evt);
            }
        });

        jLabel4.setText("Luminosidade");

        l_bri.setText("255");

        l_sat.setText("255");

        jLabel7.setText("Saturação");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(sl_bri, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(l_sat))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(sl_sat, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(l_bri)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_bri, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sl_sat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(sl_bri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_sat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bt_calc.setText("Calcular");
        bt_calc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_calcActionPerformed(evt);
            }
        });

        bt_stop.setText("Stop");
        bt_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_stopActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txt_zoom.setEditable(false);
        txt_zoom.setText("1.20");
        txt_zoom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_zoomPropertyChange(evt);
            }
        });

        jLabel5.setText("ZOOM");

        txt_itera.setText("256");

        jLabel3.setText("Iterações");

        bt_minus.setText("-");
        bt_minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_minusActionPerformed(evt);
            }
        });

        bt_plus.setText("+");
        bt_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_plusActionPerformed(evt);
            }
        });

        btn_aceleracao.setText("Aceleração");
        btn_aceleracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceleracaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_minus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_zoom, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_plus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_itera, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_aceleracao, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_plus)
                    .addComponent(txt_zoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_minus)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_aceleracao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_itera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(14, 14, 14))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tempos"));

        jLabel8.setText("Tempo Sequencial:");

        jLabel10.setText("Tempo Paralelo:");

        jLabel11.setText("Tempo Balanciado:");

        txt_seq.setEditable(false);

        txt_par.setEditable(false);

        txt_bal.setEditable(false);

        jLabel6.setText("H:M:S:ssss");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_seq, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_par, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_bal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_seq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_par, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(bt_save)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_calc, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_stop, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_calc)
                    .addComponent(bt_save)
                    .addComponent(bt_stop))
                .addGap(164, 164, 164))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 612, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_calcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_calcActionPerformed
        verificaRacio();
        f.resizeImg(Integer.parseInt(txt_width.getText() + ""), Integer.parseInt(txt_height.getText() + ""));
        f.setSaturationBrightness((float) sl_bri.getValue(), (float) sl_sat.getValue());
        defineFractal(Long.parseLong(txt_itera.getText()));
        f.initCalculateFractalGUI();
    }//GEN-LAST:event_bt_calcActionPerformed

    private void verificaRacio() {
        System.out.println("jPanel6.getWidth(): " + jPanel6.getWidth());
        System.out.println("txt_width.getText(): " + Integer.parseInt(txt_width.getText()));

        //int largJanela = jPanel6.getWidth() + jPanel2.getWidth();
        //System.out.println("largJanela: " + largJanela);

        // se o tamanho do painel do lado esquerdo + o tamanho do fratal < largura da janela
        if (jPanel6.getWidth() + Integer.parseInt(txt_width.getText()) < original.width) {
            System.out.println("MENOR");
            //jPanel2.setSize(Integer.parseInt(txt_width.getText()), Integer.parseInt(txt_height.getText()));
            //JFrame.setResizable(false);
            jPanel2.setPreferredSize(new Dimension(Integer.parseInt(txt_width.getText()), Integer.parseInt(txt_height.getText())));
            //
        } else {
            System.out.println("MAIOR");
            jPanel2.setPreferredSize(original);
            //JFrame.setResizable(true);
        }
        JFrame.pack();
        //JFrame.repaint();
    }

    private void bt_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_saveActionPerformed
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png");
        JFileChooser c = new JFileChooser();
        c.setFileFilter(filter);
        int rVal = c.showSaveDialog(this);
        File file = c.getSelectedFile();
        if (rVal == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(f.getImg(), "png", new File(file.getAbsoluteFile() + ".png"));
            } catch (IOException ex) {
                Logger.getLogger(GUIFratal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bt_saveActionPerformed

    private void rb_uhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_uhdActionPerformed
        txt_width.setText("3860");
        txt_height.setText("2060");
    }//GEN-LAST:event_rb_uhdActionPerformed

    private void sl_briStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sl_briStateChanged
        l_bri.setText(sl_bri.getValue() + "");
    }//GEN-LAST:event_sl_briStateChanged

    private void sl_satStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sl_satStateChanged
        l_sat.setText(sl_sat.getValue() + "");
    }//GEN-LAST:event_sl_satStateChanged

    private void rb_fhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_fhdActionPerformed
        txt_width.setText("1920");
        txt_height.setText("1080");
    }//GEN-LAST:event_rb_fhdActionPerformed

    private void rb_rhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_rhdActionPerformed
        txt_width.setText("1280");
        txt_height.setText("720");
    }//GEN-LAST:event_rb_rhdActionPerformed

    private void rb_sdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_sdActionPerformed
        txt_width.setText("720");
        txt_height.setText("576");
    }//GEN-LAST:event_rb_sdActionPerformed
    /*
     * Botão de stop
     */
    private void bt_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_stopActionPerformed
        f.stopCalculateFractalGUI();
    }//GEN-LAST:event_bt_stopActionPerformed

    private void txt_zoomPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_zoomPropertyChange

    }//GEN-LAST:event_txt_zoomPropertyChange

    private void bt_plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_plusActionPerformed
        double zoom = Double.parseDouble(txt_zoom.getText());
        zoom += 0.1;
        txt_zoom.setText((zoom + "").format("%.2f", zoom).replace(",", "."));
        f.setNewZoom(Double.parseDouble(txt_zoom.getText()));
    }//GEN-LAST:event_bt_plusActionPerformed

    private void bt_minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_minusActionPerformed
        double zoom = Double.parseDouble(txt_zoom.getText());
        zoom -= 0.1;
        txt_zoom.setText((zoom + "").format("%.2f", zoom).replace(",", "."));
        f.setNewZoom(Double.parseDouble(txt_zoom.getText()));
    }//GEN-LAST:event_bt_minusActionPerformed
    /**
     * Botão para calculo da acelaração
     *
     * @param evt
     */
    private void btn_aceleracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceleracaoActionPerformed
        f.resizeImg(Integer.parseInt(txt_width.getText() + ""), Integer.parseInt(txt_height.getText() + ""));
        f.setSaturationBrightness((float) sl_bri.getValue(), (float) sl_sat.getValue());
        defineFractal(Long.parseLong(txt_itera.getText()));
        // define fratal sequencial
        f.seqCalculateFractalGUI(pbar, txt_seq);

        new Thread(() -> {
            long med1 = 0;
            long med2 = 0;
            Writer writer = null;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("aceleracao.txt"), "utf-8"));
                //começar por escrever a data atual
                writer.write(dateFormat.format(date) + '\r' + '\n');
                // escrever a resolução:
                writer.write("Resolução: " + txt_width.getText() + "x" + txt_height.getText() + '\r' + '\n');
                // escrever o algoritmo
                writer.write("Algoritmo: Sequencial" + '\r' + '\n');

                for (int i = 0; i < 5; i++) {
                    f.initCalculateFractalGUI();

                    med1 += f.calculus.getTime();
                    writer.write(i + "º Calculo: " + f.calculus.getTime() + '\r' + '\n');
                    System.out.println(i + " valor: " + f.calculus.getTime());
                }
                med1 /= 5;
                writer.write("Média1: " + med1 + '\r' + '\n');
                writer.write("--------------------------------------------------" + '\r' + '\n');
                writer.write("Algoritmo: xpto" + '\r' + '\n');
                if (rb_bal.isSelected()) {
                    f.balCalculateFractalGUI(pbar, txt_bal);
                } else {
                    f.parCalculateFractalGUI(pbar, txt_par);
                }

                for (int i = 0; i < 5; i++) {
                    f.initCalculateFractalGUI();

                    med2 += f.calculus.getTime();
                    writer.write(i + "º Calculo: " + f.calculus.getTime() + '\r' + '\n');
                    System.out.println(i + " valor: " + f.calculus.getTime());
                }
                med2 /= 5;
                writer.write("Média2: " + med2 + '\r' + '\n');
                writer.write("Acelaração: " + med1 / med2);
            } catch (IOException ex) {
                // lança mensagem de erro, caso nao jea possivel criar ficheiro
                JOptionPane.showMessageDialog(jPanel1, "Ocorreu um erro ao criar o ficheiro", "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {
                }
            }
        }).start();
    }//GEN-LAST:event_btn_aceleracaoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIFratal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIFratal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIFratal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIFratal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIFratal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_calc;
    private javax.swing.JButton bt_minus;
    private javax.swing.JButton bt_plus;
    private javax.swing.JButton bt_save;
    private javax.swing.JButton bt_stop;
    private javax.swing.JButton btn_aceleracao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel l_bri;
    private javax.swing.JLabel l_sat;
    private javax.swing.JProgressBar pbar;
    private javax.swing.JRadioButton rb_bal;
    private javax.swing.JRadioButton rb_burning;
    private javax.swing.JRadioButton rb_fhd;
    private javax.swing.JRadioButton rb_madelbroth;
    private javax.swing.JRadioButton rb_par;
    private javax.swing.JRadioButton rb_rhd;
    private javax.swing.JRadioButton rb_sd;
    private javax.swing.JRadioButton rb_seq;
    private javax.swing.JRadioButton rb_uhd;
    private javax.swing.JSlider sl_bri;
    private javax.swing.JSlider sl_sat;
    private javax.swing.JTextField txt_bal;
    private javax.swing.JTextField txt_height;
    private javax.swing.JTextField txt_itera;
    private javax.swing.JTextField txt_par;
    private javax.swing.JTextField txt_seq;
    private javax.swing.JTextField txt_width;
    private javax.swing.JTextField txt_zoom;
    // End of variables declaration//GEN-END:variables
}
