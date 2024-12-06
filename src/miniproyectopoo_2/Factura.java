/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miniproyectopoo_2;

/**
 *
 * @author luisc
 */
public class Factura {
    //ATRIBUTOS PRODUCTO
    private String codigoProducto;
    private String nombreProducto;
    private double precioProducto;
    private double impuestoProducto;
    private String categoriaProducto;
    private double precioTotalProducto;
    
    //ATRIBUTO CLIENTE
    private String nombreCliente;
    private String identificacionCliente;
    private String direccionCliente;

    public Factura(String codigoProducto, String nombreProducto, double precioProducto, double impuestoProducto, String categoriaProducto, double precioTotalProducto, String nombreCliente, String identificacionCliente, String direccionCliente) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.impuestoProducto = impuestoProducto;
        this.categoriaProducto = categoriaProducto;
        this.precioTotalProducto = precioTotalProducto;
        this.nombreCliente = nombreCliente;
        this.identificacionCliente = identificacionCliente;
        this.direccionCliente = direccionCliente;
    }
    
    //GETTERS PRODUCTOS 
    public String getCodigoProducto(){
        return codigoProducto;
    }
    public String getNombreProducto(){
        return nombreProducto;
    }
    public double getPrecioProducto(){
        return precioProducto;
    }
    public double getImpuestoProducto(){
        return impuestoProducto;
    }
    public String getCategoriaProducto(){
        return categoriaProducto;
    }
    
    public double getPrecioTotalProducto(){
        return precioTotalProducto;
    }
    
    //GETTERS CLIENTE
    public String getNombreCliente(){
        return nombreCliente;
    }
    
    public String getIdentificacionCliente(){
        return identificacionCliente;
    }
    
    public String getDireccionCliente(){
        return direccionCliente;
    }
}
