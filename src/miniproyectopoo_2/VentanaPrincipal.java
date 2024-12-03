/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package miniproyectopoo_2;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author luisc
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    public VentanaPrincipal() {
        initComponents();
    }

    public void cambiarPanelContenedor1(JPanel nuevoPanel) {
        PanelContenedor1.removeAll();
        PanelContenedor1.setLayout(new BorderLayout());
        PanelContenedor1.add(nuevoPanel);
        PanelContenedor1.revalidate();
        PanelContenedor1.repaint();
        nuevoPanel.setFocusable(true);
    }

    public void cambiarPanelContenedor2(JPanel nuevoPanel) {
        PanelContenedor2.removeAll();
        PanelContenedor2.setLayout(new BorderLayout());
        PanelContenedor2.add(nuevoPanel);
        PanelContenedor2.revalidate();
        PanelContenedor2.repaint();
        nuevoPanel.setFocusable(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelContenedor1 = new javax.swing.JPanel();
        PanelContenedor2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAyuda = new javax.swing.JMenu();
        menuItemAcercaDe = new javax.swing.JMenuItem();
        menuInfo = new javax.swing.JMenu();
        menuItemContacto = new javax.swing.JMenuItem();
        menuItemSoporte = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout PanelContenedor1Layout = new javax.swing.GroupLayout(PanelContenedor1);
        PanelContenedor1.setLayout(PanelContenedor1Layout);
        PanelContenedor1Layout.setHorizontalGroup(
            PanelContenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        PanelContenedor1Layout.setVerticalGroup(
            PanelContenedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelContenedor2Layout = new javax.swing.GroupLayout(PanelContenedor2);
        PanelContenedor2.setLayout(PanelContenedor2Layout);
        PanelContenedor2Layout.setHorizontalGroup(
            PanelContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        PanelContenedor2Layout.setVerticalGroup(
            PanelContenedor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        menuAyuda.setText("Ayuda");

        menuItemAcercaDe.setText("Acerca de...");
        menuItemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAcercaDeActionPerformed(evt);
            }
        });
        menuAyuda.add(menuItemAcercaDe);

        jMenuBar1.add(menuAyuda);

        menuInfo.setText("Info");

        menuItemContacto.setText("Contactanos");
        menuItemContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemContactoActionPerformed(evt);
            }
        });
        menuInfo.add(menuItemContacto);

        menuItemSoporte.setText("Soporte");
        menuItemSoporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSoporteActionPerformed(evt);
            }
        });
        menuInfo.add(menuItemSoporte);

        jMenuBar1.add(menuInfo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelContenedor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelContenedor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelContenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelContenedor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAcercaDeActionPerformed
        String mensaje = "SOMOS TIENDA VIRTUAL PARA COMPRAR PRODUCTOS TECNOLOGICOS\n"
                + "\n"
                + "PARA CONOCER NUESTROS PRODUCTOS, DALE CLICK AL BOTON CONTINUAR!\n"
                + "\n"
                + "TODOS NUESTROS CANALES DE AYUDA Y SOPORTE, ESTAN EN NUESTRA OPCION DE 'INFO'";
        JOptionPane.showMessageDialog(this, mensaje, "AYUDA", JOptionPane.INFORMATION_MESSAGE);


    }//GEN-LAST:event_menuItemAcercaDeActionPerformed

    private void menuItemContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemContactoActionPerformed
        String mensaje = "PARA COMUNICARTE CON NOSOTROS HAZLO POR ESTOS CANALES:\n"
                + "\n"
                + "FACEBOOK: tiendavirtualluisito\n"
                + "\n"
                + "INSTAGRAM: @virtualtiendaluisito\n"
                + "\n"
                + "WHATSAPP: 1234556\n"
                + "\n"
                + "SI REQUIERES CUALQUIER TIPO DE AYUDA O SOPORTE, EN LA OPCION 'SOPORTE' ESTAN LOS CONTACTOS ESPECIALIZADOS";

        JOptionPane.showMessageDialog(this, mensaje, "CONTACTANOS", JOptionPane.INFORMATION_MESSAGE);


    }//GEN-LAST:event_menuItemContactoActionPerformed

    private void menuItemSoporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSoporteActionPerformed
        String mensaje = "SI REQUIERES UN SOPORTE ESPECALIZADO O HABLAR CON UNO DE NUESTROS ASESORES, TENEMOS LOS SIGUIENTES CANALES:\n"
                + "\n"
                + "CORREO ELECTRONICO: tiendavirtualluisito@gmail.com y soportevirtualtiendaluisito@gmail.com \n "
                + "\n"
                + "WHATSAPP: 098765431\n"
                + "\n"
                + "POR ESTOS CANALES NUESTROS ASESORES ESTARAN DISPONIBLES ANTE CUALQUIER DUDA O INQUIETUD!";

        JOptionPane.showMessageDialog(this, mensaje, "SOPORTE", JOptionPane.INFORMATION_MESSAGE);


    }//GEN-LAST:event_menuItemSoporteActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelContenedor1;
    private javax.swing.JPanel PanelContenedor2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenu menuInfo;
    private javax.swing.JMenuItem menuItemAcercaDe;
    private javax.swing.JMenuItem menuItemContacto;
    private javax.swing.JMenuItem menuItemSoporte;
    // End of variables declaration//GEN-END:variables
}
