/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package miniproyectopoo_2;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author luisc
 */
public class PanelAdmin extends javax.swing.JPanel {

    /**
     * Creates new form PanelAdmin
     */
    private VentanaPrincipal ventanaPrincipal;
    private VentanaInicio ventanaInicio;
    private TablaProductosAdmin tablaProductosAdmin;
    private TablaProductosUsuario tablaProductosUsuario;

    public PanelAdmin(VentanaInicio ventanaInicio, VentanaPrincipal ventanaPrincipal, TablaProductosAdmin tablaProductosAdmin, TablaProductosUsuario tablaProductosUsuario) {
        initComponents();
        this.ventanaInicio = ventanaInicio;
        this.ventanaPrincipal = ventanaPrincipal;
        this.tablaProductosAdmin = tablaProductosAdmin;

    }

    public void setTablaProductosAdmin(TablaProductosAdmin tablaProductosAdmin) {
        this.tablaProductosAdmin = tablaProductosAdmin;
    }

    public void setTablaProductosUsuario(TablaProductosUsuario tablaProductosUsuario){
        this.tablaProductosUsuario = tablaProductosUsuario;
                
    }
    //GETTERS DE LOS CAMPOS DE TEXTO
    public String getCampoCodigoProducto() {
        return campoCodigoProducto.getText();
    }

    public String getCampoNombreProducto() {
        return campoNombreProducto.getText();
    }

    public String getCampoPrecioProducto() {
        return campoPrecioProducto.getText();
    }

    public String getCampoImpuestoProducto() {
        return campoImpuestoProducto.getText();
    }

    public String getCampoCategoriaProducto() {
        return campoCategoriaProducto.getText();
    }

    public String getCampoTotalProducto() {
        return campoTotalProducto.getText();
    }

    //SETTERS DE LOS CAMPOS DE TEXTO
    public void setCampoCodigoProducto(String texto) {
        this.campoCodigoProducto.setText(texto);
    }

    public void setCampoNombreProducto(String texto) {
        this.campoNombreProducto.setText(texto);
    }

    public void setCampoPrecioProducto(String texto) {
        this.campoPrecioProducto.setText(texto);
    }

    public void setCampoImpuestoProducto(String texto) {
        this.campoImpuestoProducto.setText(texto);
    }

    public void setCampoCategoriaProducto(String texto) {
        this.campoCategoriaProducto.setText(texto);
    }

    public void setCampoTotalProducto(String texto) {
        this.campoTotalProducto.setText(texto);
    }

    //METODO PARA OBTENER EL PRECIO TOTAL DEL PRODUCTO.
    public double precioTotalProducto() {
        double precioProducto = Double.parseDouble(getCampoPrecioProducto());
        double impuestoProducto = Double.parseDouble(getCampoImpuestoProducto());
        double precioTotalProducto = precioProducto + impuestoProducto;
        return precioTotalProducto;
    }

        //Metodo para crear y guardar datos en archivo xml
    public void guardarXML(String nombreArchivo) {
        try {
            DefaultTableModel modelo = tablaProductosAdmin.getModeloTabla();
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            Element raiz = documento.createElement("productos");
            documento.appendChild(raiz);

            for (int i = 0; i < modelo.getRowCount(); i++) {
                Element producto = documento.createElement("producto");

                Element codigo = documento.createElement("codigo");
                codigo.appendChild(documento.createTextNode(modelo.getValueAt(i, 0).toString()));
                producto.appendChild(codigo);

                Element nombre = documento.createElement("nombre");
                nombre.appendChild(documento.createTextNode(modelo.getValueAt(i, 1).toString()));
                producto.appendChild(nombre);

                Element precio = documento.createElement("precio");
                precio.appendChild(documento.createTextNode(modelo.getValueAt(i, 2).toString()));
                producto.appendChild(precio);

                
                Element impuesto = documento.createElement("impuesto");
                impuesto.appendChild(documento.createTextNode(modelo.getValueAt(i, 3).toString()));
                producto.appendChild(impuesto);
                
                Element categoria = documento.createElement("categoria");
                categoria.appendChild(documento.createTextNode(modelo.getValueAt(i, 4).toString()));
                producto.appendChild(categoria);
                
                Element total = documento.createElement("total");
                total.appendChild(documento.createTextNode(modelo.getValueAt(i, 5).toString()));
                producto.appendChild(total);
                raiz.appendChild(producto);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(documento);
            StreamResult streamResult = new StreamResult(new File(nombreArchivo));
            transformer.transform(domSource, streamResult);

            System.out.println("Archivo XML generado correctamente: " + nombreArchivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    //METODO PARA CARGAR EL ARCHIVO XML EN LA TABLA 
    public void cargarDesdeXML(String nombreArchivo) {
    try {
        DefaultTableModel modelo = tablaProductosAdmin.getModeloTabla();
                
        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo XML no existe. Se creará uno nuevo cuando se guarde.");
            return;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(archivo);

        NodeList listaProductos = documento.getElementsByTagName("producto");

        modelo.setRowCount(0);

        for (int i = 0; i < listaProductos.getLength(); i++) {
            Node productoNode = listaProductos.item(i);

            if (productoNode.getNodeType() == Node.ELEMENT_NODE) {
                Element producto = (Element) productoNode;

                String codigo = producto.getElementsByTagName("codigo").item(0).getTextContent();
                String nombre = producto.getElementsByTagName("nombre").item(0).getTextContent();
                String precio = producto.getElementsByTagName("precio").item(0).getTextContent();
                String impuesto = producto.getElementsByTagName("impuesto").item(0).getTextContent();
                String categoria = producto.getElementsByTagName("categoria").item(0).getTextContent();
                String total = producto.getElementsByTagName("total").item(0).getTextContent();

                modelo.addRow(new Object[]{codigo, nombre, precio, impuesto,categoria,total});
            }
        }

        System.out.println("Datos cargados desde el archivo XML.");

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar los datos desde el archivo XML");
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

        jLabel1 = new javax.swing.JLabel();
        botonVolver = new javax.swing.JButton();
        etiquetaTitulo1 = new javax.swing.JLabel();
        etiquetaTitulo2 = new javax.swing.JLabel();
        etiquetaTitulo3 = new javax.swing.JLabel();
        etiquetaTitulo4 = new javax.swing.JLabel();
        etiquetaTitulo5 = new javax.swing.JLabel();
        etiquetaTitulo6 = new javax.swing.JLabel();
        etiquetaTitulo7 = new javax.swing.JLabel();
        campoCodigoProducto = new javax.swing.JTextField();
        campoNombreProducto = new javax.swing.JTextField();
        campoPrecioProducto = new javax.swing.JTextField();
        campoImpuestoProducto = new javax.swing.JTextField();
        campoCategoriaProducto = new javax.swing.JTextField();
        campoTotalProducto = new javax.swing.JTextField();
        botonGuardar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonCargar = new javax.swing.JButton();
        botonTrasnferirProductos = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setText("BIENVENIDO");

        botonVolver.setText("VOLVER");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        etiquetaTitulo1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        etiquetaTitulo1.setText("CREACION Y MODIFICACION DE PRODUCTOS");

        etiquetaTitulo2.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo2.setText("CODIGO DEL PRODUCTO:");

        etiquetaTitulo3.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo3.setText("NOMBRE DEL PRODUCTO:");

        etiquetaTitulo4.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo4.setText("PRECIO DEL PRODUCTO:");

        etiquetaTitulo5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo5.setText("IMPUESTO DEL PRODUCTO:");

        etiquetaTitulo6.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo6.setText("CATEGORIA DEL PRODUCTO:");

        etiquetaTitulo7.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        etiquetaTitulo7.setText("TOTAL:");

        campoTotalProducto.setEditable(false);

        botonGuardar.setText("GUARDAR");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        botonEditar.setText("EDITAR");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });

        botonEliminar.setText("ELIMINAR");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonCargar.setText("CARGAR");
        botonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarActionPerformed(evt);
            }
        });

        botonTrasnferirProductos.setText("TRANSFERIR PRODUCTOS");
        botonTrasnferirProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTrasnferirProductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonTrasnferirProductos)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(etiquetaTitulo7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 118, Short.MAX_VALUE)
                                .addComponent(botonGuardar)
                                .addGap(78, 78, 78)
                                .addComponent(botonEditar)
                                .addGap(202, 202, 202)
                                .addComponent(botonCargar)
                                .addGap(74, 74, 74))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(etiquetaTitulo5)
                                            .addGap(18, 18, 18)
                                            .addComponent(campoImpuestoProducto))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(etiquetaTitulo6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(campoCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(etiquetaTitulo2)
                                                .addComponent(etiquetaTitulo3)
                                                .addComponent(etiquetaTitulo4))
                                            .addGap(33, 33, 33)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(campoPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(campoNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(campoCodigoProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(204, 204, 204)
                                        .addComponent(campoTotalProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonEliminar)
                            .addComponent(etiquetaTitulo1))
                        .addGap(128, 128, 128)
                        .addComponent(botonVolver)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(etiquetaTitulo1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(botonVolver)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaTitulo2)
                            .addComponent(campoCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaTitulo3)
                            .addComponent(campoNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaTitulo4)
                            .addComponent(campoPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaTitulo5)
                            .addComponent(campoImpuestoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaTitulo6)
                            .addComponent(campoCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etiquetaTitulo7)
                            .addComponent(campoTotalProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonEliminar)
                            .addComponent(botonEditar)
                            .addComponent(botonGuardar)
                            .addComponent(botonCargar))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonTrasnferirProductos)))
                .addContainerGap())
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

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        DefaultTableModel modelo = tablaProductosAdmin.getModeloTabla();
        String codigoProducto = getCampoCodigoProducto();
        String nombreProducto = getCampoNombreProducto();
        String precioProducto = getCampoPrecioProducto();
        String impuestoProducto = getCampoImpuestoProducto();
        String categoriaProducto = getCampoCategoriaProducto();
        double precioTotalProducto = precioTotalProducto();

        if (codigoProducto.isEmpty() || nombreProducto.isEmpty() || precioProducto.isEmpty() || impuestoProducto.isEmpty()
                || categoriaProducto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "TODOS LOS CAMPOS TIENE QUE ESTAR COMPLETOS", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        } else {
            Object nuevaFila[] = {codigoProducto, nombreProducto, precioProducto, impuestoProducto, categoriaProducto, precioTotalProducto};
            modelo.addRow(nuevaFila);
            guardarXML("productos.xml");
            
            campoCodigoProducto.setText("");
            campoNombreProducto.setText("");
            campoPrecioProducto.setText("");
            campoImpuestoProducto.setText("");
            campoCategoriaProducto.setText("");
            campoTotalProducto.setText("");
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        DefaultTableModel modelo = tablaProductosAdmin.getModeloTabla();
        int filaSeleccionada = tablaProductosAdmin.obtenerFila();
        if (filaSeleccionada != -1) {
            String codigoProducto = getCampoCodigoProducto();
            String nombreProducto = getCampoNombreProducto();
            String precioProducto = getCampoPrecioProducto();
            String impuestoProducto = getCampoImpuestoProducto();
            String categoriaProducto = getCampoCategoriaProducto();
            double precioTotalProducto = precioTotalProducto();

            if (codigoProducto.isEmpty() || nombreProducto.isEmpty() || precioProducto.isEmpty() || impuestoProducto.isEmpty()
                    || categoriaProducto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "TODOS LOS CAMPOS TIENE QUE ESTAR COMPLETOS", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            } else {
                modelo.setValueAt(codigoProducto, filaSeleccionada, 0);
                modelo.setValueAt(nombreProducto, filaSeleccionada, 1);
                modelo.setValueAt(precioProducto, filaSeleccionada, 2);
                modelo.setValueAt(impuestoProducto, filaSeleccionada, 3);
                modelo.setValueAt(categoriaProducto, filaSeleccionada, 4);
                modelo.setValueAt(precioTotalProducto, filaSeleccionada, 5);

                guardarXML("productos.xml");
                
                campoCodigoProducto.setText("");
                campoNombreProducto.setText("");
                campoPrecioProducto.setText("");
                campoImpuestoProducto.setText("");
                campoCategoriaProducto.setText("");
                campoTotalProducto.setText("");

            }
        }


    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        DefaultTableModel modelo = tablaProductosAdmin.getModeloTabla();
        int filaSeleccionada = tablaProductosAdmin.obtenerFila();
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿ESTAS SEGURO DE ELIMINAR EL PRODUCTO?", "CONFIRMACION", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (filaSeleccionada != -1) {
                modelo.removeRow(filaSeleccionada);
                
                guardarXML("productos.xml");
                
                campoCodigoProducto.setText("");
                campoNombreProducto.setText("");
                campoPrecioProducto.setText("");
                campoImpuestoProducto.setText("");
                campoCategoriaProducto.setText("");
                campoTotalProducto.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(this, "HAZ CANCELADO!");
        }


    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarActionPerformed

        cargarDesdeXML("productos.xml");

    }//GEN-LAST:event_botonCargarActionPerformed

    private void botonTrasnferirProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTrasnferirProductosActionPerformed
        int filaSeleccionada = tablaProductosAdmin.obtenerFila();
        DefaultTableModel modeloOrigen = tablaProductosAdmin.getModeloTabla();
        DefaultTableModel modeloDestino = tablaProductosUsuario.getModeloTabla();
        if(filaSeleccionada != -1){
            String codigoProducto = (String) modeloOrigen.getValueAt(filaSeleccionada, 0);
            String nombreProducto = (String) modeloOrigen.getValueAt(filaSeleccionada, 1);  
            String categoriaProducto = (String) modeloOrigen.getValueAt(filaSeleccionada, 4);
            String precioProducto = (String) modeloOrigen.getValueAt(filaSeleccionada, 5);
            
            Object nuevaFila[] = {codigoProducto , nombreProducto , categoriaProducto , precioProducto};
            modeloDestino.addRow(nuevaFila);
            JOptionPane.showMessageDialog(this, "PRODUCTOS TRANSFERIDOS CORRECTAMENTE");
        }else{
            JOptionPane.showMessageDialog(this, "SELECCIONA UNA FILA!!!");
        }

        

    }//GEN-LAST:event_botonTrasnferirProductosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCargar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonTrasnferirProductos;
    private javax.swing.JButton botonVolver;
    private javax.swing.JTextField campoCategoriaProducto;
    private javax.swing.JTextField campoCodigoProducto;
    private javax.swing.JTextField campoImpuestoProducto;
    private javax.swing.JTextField campoNombreProducto;
    private javax.swing.JTextField campoPrecioProducto;
    private javax.swing.JTextField campoTotalProducto;
    private javax.swing.JLabel etiquetaTitulo1;
    private javax.swing.JLabel etiquetaTitulo2;
    private javax.swing.JLabel etiquetaTitulo3;
    private javax.swing.JLabel etiquetaTitulo4;
    private javax.swing.JLabel etiquetaTitulo5;
    private javax.swing.JLabel etiquetaTitulo6;
    private javax.swing.JLabel etiquetaTitulo7;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
