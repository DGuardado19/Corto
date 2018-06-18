/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import conexion.Conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filtro;
/**
 *
 * @author DGuardado <david.guardado at guardado.org>
 */
public class FiltroDao implements metodos<Filtro> {
    
     private static final String SQL_INSERT = "INSERT INTO productos (id, nombre, codigo, tipo, cantidad, precio, disponibilidad) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET marca=?, stock =?, existencia=? WHERE codFiltro=?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE codFiltros=?";
    private static final String SQL_READ = "SELECT* FROM productos WHERE codFiltro=?";
    private static final String SQL_READALL = "SELECT *FROM productos";
    public static final Conexion con=Conexion.conectar();

    @Override

    public boolean create(Filtro g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCodigo());
            ps.setString(2, g.getTipo());
            ps.setInt(3, g.getCantidad());
            ps.setBoolean(4, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getTipo());
            ps.setInt(2, c.getCantidad());
            ps.setBoolean(3, c.getDisponibilidad());
            ps.setString(4, c.getCodigo());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Filtro read(Object key) {
        Filtro f = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
           
                f = new Filtro(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getDouble(7));
            }
            rs.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return f;
        }
    

    @Override
    public ArrayList<Filtro> readAll() {
    ArrayList<Filtro> all=new ArrayList();
    Statement s;
    ResultSet rs;
    try{
        s= con.getCnx().prepareStatement(SQL_READALL);
        rs =s.executeQuery(SQL_READALL);
        while(rs.next()){
            all.add(new Filtro(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getDouble(7)));
        }rs.close();
    }catch (SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return all;
    }

}
