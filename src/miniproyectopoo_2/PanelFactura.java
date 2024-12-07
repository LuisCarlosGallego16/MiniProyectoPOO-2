/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package miniproyectopoo_2;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisc
 */
public class PanelFactura extends javax.swing.JPanel {

    /**
     * Creates new form PanelFactura
     */
    private VentanaInicio ventanaInicio;
    private VentanaPrincipal ventanaPrincipal;
    private TablaFacturas tablaFacturas;
    private FormularioFactura formularioFactura;
    public PanelFactura(VentanaInicio ventanaInicio,VentanaPrincipal ventanaPrincipal,TablaFacturas tablaFacturas, FormularioFactura formularioFactura) {
        initComponents();
        this.ventanaInicio = ventanaInicio;
        this.ventanaPrincipal = ventanaPrincipal;
        this.tablaFacturas = tablaFacturas;
        this.formularioFactura = formularioFactura;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botonEliminar = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        botonCargar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setText("INFORMACION FACTURACION");

        botonEliminar.setText("ELIMINAR");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonVolver.setText("VOLVER");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        botonCargar.setText("CARGAR");
        botonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addComponent(botonEliminar)
                .addGap(81, 81, 81)
                .addComponent(botonVolver)
                .addContainerGap(268, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(226, 226, 226))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonCargar)
                        .addGap(336, 336, 336))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEliminar)
                    .addComponent(botonVolver))
                .addGap(18, 18, 18)
                .addComponent(botonCargar)
                .addContainerGap(99, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
               int confirmacion = JOptionPane.showConfirmDialog(this, "¿ESTAS SEGURO QUE QUIERES SALIR?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            ventanaPrincipal.dispose();
            ventanaInicio.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "SIGUE COMPRANDO!!!");
        }
        
        
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        DefaultTableModel modeloFactura = tablaFacturas.getModeloTabla();
        int filaSeleccionada = tablaFacturas.obtenerFila();
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿ESTAS SEGURO DE ELIMINAR LA FACTURA?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
        if(confirmacion == JOptionPane.YES_OPTION){
            if(filaSeleccionada != -1){
                modeloFactura.removeRow(filaSeleccionada);
                
            }else{
                JOptionPane.showMessageDialog(this, "SELECCIONA UNA FACTURA");
            }
        }



    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarActionPerformed
        
        formularioFactura.cargarArchivoJSON();
        

    }//GEN-LAST:event_botonCargarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCargar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
