/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package miniproyectopoo_2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisc
 */
public class FormularioFactura extends javax.swing.JDialog {

    /**
     * Creates new form FormularioFactura
     */
    private TablaProductosAdmin tablaProductosAdmin;
    private TablaFacturas tablaFacturas;

    public FormularioFactura(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    public void setTablaFactura(TablaFacturas tablaFacturas) {
        this.tablaFacturas = tablaFacturas;
    }

    //GETTER
    public String getCampoFacturaNombre() {
        return campoFacturaNombre.getText();
    }

    public String getCampoFacturaIdentificacion() {
        return campoFacturaCategoria.getText();
    }

    public String getCampoFacturaDireccion() {
        return campoFacturaDirreccion.getText();
    }

    public String getCampoFacturaCodigoProducto() {
        return campoFacturaCodigo.getText();
    }

    public String getCampoFacturaNombreProducto() {
        return campoFacturaNombreProducto.getText();
    }

    public String getCampoFacturaPrecioProducto() {
        return campoFacturaPrecio.getText();
    }

    public String getCampoFacturaImpuestoProducto() {
        return campoFacturaImpuesto.getText();
    }

    public String getCampoFacturaCategoriaProducto() {
        return campoFacturaCategoria.getText();
    }

    public String getCampoFacturaTotalProducto() {
        return campoFacturaTotal.getText();
    }

    // SETTERS
    public void setCampoFacturaCodigoProducto(String texto) {
        this.campoFacturaCodigo.setText(texto);
    }

    public void setCampoFacturaNombreProducto(String texto) {
        this.campoFacturaNombreProducto.setText(texto);
    }

    public void setCampoFacturaPrecioProducto(String texto) {
        this.campoFacturaPrecio.setText(texto);

    }

    public void setCampoFacturaImpuestoProducto(String texto) {
        this.campoFacturaImpuesto.setText(texto);

    }

    public void setCampoFacturaCategoriaProducto(String texto) {
        this.campoFacturaCategoria.setText(texto);
    }

    public void setCampoFacturaPrecioTotalProducto(String texto) {
        this.campoFacturaTotal.setText(texto);
    }

    public void guardarArchivoJSON() {
    try {
        DefaultTableModel modeloFactura = tablaFacturas.getModeloTabla();
        List<Factura> listaFacturas = new ArrayList<>();

        for (int i = 0; i < modeloFactura.getRowCount(); i++) {
            String codigoProducto = modeloFactura.getValueAt(i, 0).toString();
            String nombreProducto = modeloFactura.getValueAt(i, 1).toString();
            double precioProducto = Double.parseDouble(modeloFactura.getValueAt(i, 2).toString());
            double impuestoProducto = Double.parseDouble(modeloFactura.getValueAt(i, 3).toString());
            String categoriaProducto = modeloFactura.getValueAt(i, 4).toString();
            double precioTotalProducto = Double.parseDouble(modeloFactura.getValueAt(i, 5).toString());
            String nombreCliente = modeloFactura.getValueAt(i, 6).toString();
            String identificacionCliente = modeloFactura.getValueAt(i, 7).toString();
            String direccionCliente = modeloFactura.getValueAt(i, 8).toString();

            Factura factura = new Factura(codigoProducto, nombreProducto, precioProducto, impuestoProducto,
                    categoriaProducto, precioTotalProducto, nombreCliente, identificacionCliente, direccionCliente);

            listaFacturas.add(factura);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter file = new FileWriter("facturas.json")) {
            gson.toJson(listaFacturas, file);  
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente en el archivo JSON");
            
        } catch (IOException e) { //MANEJO EXCEPCION IO, MANEJO CUANDO HAY PROBLEMAS PARA ESCRITURA/LECTURA DE ARCHIVOS 
            JOptionPane.showMessageDialog(this, "Error al guardar los datos en el archivo JSON: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  
        }

    } catch (Exception e) { //MANEJO DE EXCEPCION
        JOptionPane.showMessageDialog(this, "Error en el proceso de guardar datos: " + e.getMessage(),
                "Error General", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();  
    }
}

    
    //METODO PARA CARGAR ARCHIVO FACTURA DE JSON A LA TABLA.
public void cargarArchivoJSON() {
    DefaultTableModel modeloFactura = tablaFacturas.getModeloTabla();

    try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\luisc\\OneDrive\\Documentos\\NetBeansProjects\\MiniProyectoPOO_2\\facturas.json"))) {
        Gson gson = new Gson();
        java.lang.reflect.Type tipoFactura = new TypeToken<List<Factura>>() {
        }.getType();

        List<Factura> facturas = gson.fromJson(reader, tipoFactura);

        modeloFactura.setRowCount(0);

        for (int i = 0; i < facturas.size(); i++) {
            Factura factura = facturas.get(i);
            Object[] fila = {
                factura.getCodigoProducto(),
                factura.getNombreProducto(),
                factura.getPrecioProducto(),
                factura.getImpuestoProducto(),
                factura.getCategoriaProducto(),
                factura.getPrecioTotalProducto(),
                factura.getNombreCliente(),
                factura.getIdentificacionCliente(),
                factura.getDireccionCliente()
            };
            modeloFactura.addRow(fila);
        }

        JOptionPane.showMessageDialog(this, "Datos cargados correctamente desde el archivo JSON.");
    } catch (FileNotFoundException e) { //IMPLEMENTACION DE LA EXEPCION POR SI EL ARCHIVO NO SE ENCUENTRA.
        JOptionPane.showMessageDialog(this, "El archivo JSON no fue encontrado: " + e.getMessage(), "Error de archivo", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); 
                
    } catch (IOException e) {
        //Se captura otra exepcion para cualquier otro error de entrada/salida
        JOptionPane.showMessageDialog(this, "Error de lectura del archivo JSON: " + e.getMessage(), "Error de I/O", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar el archivo JSON: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    //METODO PARA LIMPIAR LOS CAMPOS DEL FORMULARIO PARA CREAR FACTURA.
    public void limpiarCamposFactura() {
        campoFacturaCodigo.setText("");
        campoFacturaNombreProducto.setText("");
        campoFacturaPrecio.setText("");
        campoFacturaImpuesto.setText("");
        campoFacturaCategoria.setText("");
        campoFacturaTotal.setText("");

        campoFacturaNombre.setText("");
        campoFacturaIdentificacion.setText("");
        campoFacturaDirreccion.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiquetaTitulo1 = new javax.swing.JLabel();
        etiquetaTitulo2 = new javax.swing.JLabel();
        etiquetaTitulo3 = new javax.swing.JLabel();
        etiquetaTitulo4 = new javax.swing.JLabel();
        etiquetaTitulo5 = new javax.swing.JLabel();
        etiquetaTitulo6 = new javax.swing.JLabel();
        etiquetaTitulo7 = new javax.swing.JLabel();
        etiquetaTitulo8 = new javax.swing.JLabel();
        etiquetaTitulo9 = new javax.swing.JLabel();
        etiquetaTitulo10 = new javax.swing.JLabel();
        etiquetaTitulo11 = new javax.swing.JLabel();
        etiquetaTitulo12 = new javax.swing.JLabel();
        campoFacturaNombre = new javax.swing.JTextField();
        campoFacturaIdentificacion = new javax.swing.JTextField();
        campoFacturaDirreccion = new javax.swing.JTextField();
        campoFacturaCodigo = new javax.swing.JTextField();
        campoFacturaNombreProducto = new javax.swing.JTextField();
        campoFacturaPrecio = new javax.swing.JTextField();
        campoFacturaImpuesto = new javax.swing.JTextField();
        campoFacturaCategoria = new javax.swing.JTextField();
        campoFacturaTotal = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        etiquetaTitulo1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        etiquetaTitulo1.setText("FACTURA");

        etiquetaTitulo2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo2.setText("INGRESE SUS DATOS PARA GENERAR SU FACTURA");

        etiquetaTitulo3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo3.setText("NOMBRE:");

        etiquetaTitulo4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo4.setText("IDENTIFICACION:");

        etiquetaTitulo5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo5.setText("DIRECCION:");

        etiquetaTitulo6.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo6.setText("DATOS DEL PRODUCTO A COMPRAR");

        etiquetaTitulo7.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo7.setText("CODIGO DEL PRODUCTO:");

        etiquetaTitulo8.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo8.setText("NOMBRE DEL PRODUCTO:");

        etiquetaTitulo9.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo9.setText("PRECIO DEL PRODUCTO:");

        etiquetaTitulo10.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo10.setText("IMPUESTO DEL PRODUCTO:");

        etiquetaTitulo11.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo11.setText("CATEGORIA DEL PRODUCTO:");

        etiquetaTitulo12.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo12.setText("TOTAL:");

        campoFacturaCodigo.setEditable(false);

        campoFacturaNombreProducto.setEditable(false);

        campoFacturaPrecio.setEditable(false);

        campoFacturaImpuesto.setEditable(false);

        campoFacturaCategoria.setEditable(false);

        campoFacturaTotal.setEditable(false);

        botonGuardar.setText("GUARDAR");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonCancelar.setText("CANCELAR");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(etiquetaTitulo1)
                        .addGap(303, 303, 303))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(etiquetaTitulo2)
                        .addGap(161, 161, 161))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(etiquetaTitulo6)
                        .addGap(210, 210, 210))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(etiquetaTitulo5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoFacturaDirreccion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(etiquetaTitulo4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoFacturaIdentificacion))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(etiquetaTitulo3)
                        .addGap(27, 27, 27)
                        .addComponent(campoFacturaNombre))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaTitulo7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoFacturaCodigo))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaTitulo8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoFacturaNombreProducto))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaTitulo9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoFacturaPrecio))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaTitulo10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoFacturaImpuesto))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(botonGuardar)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(etiquetaTitulo11)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(57, 57, 57)
                                            .addComponent(etiquetaTitulo12))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoFacturaCategoria)
                                    .addComponent(campoFacturaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))))))
                .addGap(210, 210, 210))
            .addGroup(layout.createSequentialGroup()
                .addGap(417, 417, 417)
                .addComponent(botonCancelar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(etiquetaTitulo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaTitulo2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo3)
                    .addComponent(campoFacturaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo4)
                    .addComponent(campoFacturaIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo5)
                    .addComponent(campoFacturaDirreccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(etiquetaTitulo6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo7)
                    .addComponent(campoFacturaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo8)
                    .addComponent(campoFacturaNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTitulo9)
                    .addComponent(campoFacturaPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo10)
                    .addComponent(campoFacturaImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo11)
                    .addComponent(campoFacturaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaTitulo12)
                    .addComponent(campoFacturaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar)
                    .addComponent(botonCancelar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿ESTAS SEGURO DE CANCELAR?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "SIGUE LLENADO LOS CAMPOS CON TU INFORMACION!");
        }
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        DefaultTableModel modeloFactura = tablaFacturas.getModeloTabla();

        //INFORMACION DEL PRODUCTO
        String codigoProducto = getCampoFacturaCodigoProducto();
        String nombreProducto = getCampoFacturaNombreProducto();
        String precioProducto = getCampoFacturaPrecioProducto();
        String impuestoProducto = getCampoFacturaImpuestoProducto();
        String categoriaProducto = getCampoFacturaCategoriaProducto();
        String precioTotal = getCampoFacturaTotalProducto();

        //INFORMACION DEL CLIENTE 
        String nombreCliente = getCampoFacturaNombre();
        String identificacionCliente = getCampoFacturaIdentificacion();
        String direccionCliente = getCampoFacturaDireccion();

        if (nombreCliente.isEmpty() || identificacionCliente.isEmpty() || direccionCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "LOS CAMPOS DE TU INFORMACION PERSONAL, DEBEN DE ESTAR COMPLETAMENTE DILIGENCIADOS");
        } else {
            Object nuevaFila[] = {codigoProducto, nombreProducto, precioProducto, impuestoProducto, categoriaProducto, precioTotal, nombreCliente, identificacionCliente, direccionCliente};
            modeloFactura.addRow(nuevaFila);
            JOptionPane.showMessageDialog(this, "SE HA GENERADO CORRECTAMENTE LA FACTURA!");
            guardarArchivoJSON();
            this.dispose();
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioFactura dialog = new FormularioFactura(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JTextField campoFacturaCategoria;
    private javax.swing.JTextField campoFacturaCodigo;
    private javax.swing.JTextField campoFacturaDirreccion;
    private javax.swing.JTextField campoFacturaIdentificacion;
    private javax.swing.JTextField campoFacturaImpuesto;
    private javax.swing.JTextField campoFacturaNombre;
    private javax.swing.JTextField campoFacturaNombreProducto;
    private javax.swing.JTextField campoFacturaPrecio;
    private javax.swing.JTextField campoFacturaTotal;
    private javax.swing.JLabel etiquetaTitulo1;
    private javax.swing.JLabel etiquetaTitulo10;
    private javax.swing.JLabel etiquetaTitulo11;
    private javax.swing.JLabel etiquetaTitulo12;
    private javax.swing.JLabel etiquetaTitulo2;
    private javax.swing.JLabel etiquetaTitulo3;
    private javax.swing.JLabel etiquetaTitulo4;
    private javax.swing.JLabel etiquetaTitulo5;
    private javax.swing.JLabel etiquetaTitulo6;
    private javax.swing.JLabel etiquetaTitulo7;
    private javax.swing.JLabel etiquetaTitulo8;
    private javax.swing.JLabel etiquetaTitulo9;
    // End of variables declaration//GEN-END:variables
}
