/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {

    private int id;
    private String codigo, tipo, nombre;
    private double precio;
    private int cantidad;
    private boolean disponibilidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Filtro() {
    }

    public Filtro(int id, String codigo, int cantidad, boolean disponibilidad, String tipo, String nombre, double precio) {
        this.id = id;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.disponibilidad = disponibilidad;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Filtro(String codigo, int cantidad, boolean disponibilidad, String tipo, String nombre, double precio) {
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.disponibilidad = disponibilidad;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Filtro(int cantidad, boolean disponibilidad, String tipo, String nombre, double precio) {
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }
}
