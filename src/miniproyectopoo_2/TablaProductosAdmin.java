/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package miniproyectopoo_2;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisc
 */
public class TablaProductosAdmin extends javax.swing.JPanel {

    /**
     * Creates new form TablaProductosAdmin
     */
    private DefaultTableModel modelo;
    private PanelAdmin panelAdmin;
    public TablaProductosAdmin(PanelAdmin panelAdmin) {
        initComponents();
        this.panelAdmin = panelAdmin;
        modelo = getModeloTabla();
    }

    //Metodo para obtener el modelo de la tabla
    public DefaultTableModel getModeloTabla() {
        return (DefaultTableModel) getTabla().getModel();
    }

    //OBTENER TABLA
    public JTable getTabla() {
        return jTable1;
    }

    //metodo para obtener la fila de la tabla.
      public int obtenerFila() {
        return getTabla().getSelectedRow();
    }

    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "PRECIO", "IMPUESTO", "CATEGORIA", "TOTAL"
            }
        ));
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       int filaSeleccionada = obtenerFila();
        if(filaSeleccionada != -1){
            //OBTENEMOS LOS DATOS DE LA TABLA.
            String codigoProducto = (String) modelo.getValueAt(filaSeleccionada, 0);
            String nombreProducto = (String) modelo.getValueAt(filaSeleccionada,1);
            String precioProducto = (String) modelo.getValueAt(filaSeleccionada, 2).toString();
            String impuestoProducto = (String) modelo.getValueAt(filaSeleccionada, 3).toString();
            String categoriaProducto = (String) modelo.getValueAt(filaSeleccionada, 4);
            String totalProducto = (String) modelo.getValueAt(filaSeleccionada, 5).toString();
            
            panelAdmin.setCampoCodigoProducto(codigoProducto);
            panelAdmin.setCampoNombreProducto(nombreProducto);
            panelAdmin.setCampoPrecioProducto(precioProducto);
            panelAdmin.setCampoImpuestoProducto(impuestoProducto);
            panelAdmin.setCampoCategoriaProducto(categoriaProducto);
            panelAdmin.setCampoTotalProducto(totalProducto);
            
        }
    }//GEN-LAST:event_jTable1MouseClicked

    
            

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
