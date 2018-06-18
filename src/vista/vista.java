/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class vista extends JFrame {

    public JLabel lblCodigo, lblMarca, lblStock, lblExistencia, lblnombre, lblprecio;

    public JTextField codigo, descripcion, stock, nombre, precio, cantidad;
    public JComboBox marca;

    ButtonGroup Disponibilidad = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 20;

    DefaultTableModel tm;

    public vista() {
        //hicieron falta un par de cosas, falta de tiempo
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
       //  llenarTabla();
        Container container = getContentPane();
        container.add(precio);
        container.add(nombre);
        container.add(lblnombre);
        container.add(lblCodigo);
        container.add(lblMarca);
        container.add(lblStock);
        container.add(lblExistencia);
        container.add(codigo);
        container.add(marca);
        container.add(stock);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(table);
        container.add(lblprecio);
        container.add(limpiar);
        setSize(700, 500);
        eventos();
    }

    public final void agregarLabels() {
        lblprecio = new JLabel("Precio");
        lblnombre = new JLabel("Nombre");
        lblCodigo = new JLabel("Codigo");
        lblMarca = new JLabel("Tipo");
        lblStock = new JLabel("Cantidad");
        lblExistencia = new JLabel("Disponibilidad");
        lblprecio.setBounds(10, 70, ANCHOC, ALTOC);
        lblnombre.setBounds(10, 10, ANCHOC, ALTOC);
        lblCodigo.setBounds(10, 40, ANCHOC, ALTOC);
        lblMarca.setBounds(300, 40, ANCHOC, ALTOC);
        lblStock.setBounds(10, 100, ANCHOC, ALTOC);
        lblExistencia.setBounds(10, 140, ANCHOC, ALTOC);
    }

    public final void formulario() {
        precio = new JTextField();
        nombre = new JTextField();
        codigo = new JTextField();
        marca = new JComboBox();
        stock = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();

        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        marca.addItem("a");
        marca.addItem("b");
        marca.addItem("c");
        marca.addItem("d");

        Disponibilidad = new ButtonGroup();
        Disponibilidad.add(si);
        Disponibilidad.add(no);
        nombre.setBounds(140, 40, ANCHOC, ALTOC);
        precio.setBounds(140,70, ANCHOC, ALTOC);
        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        marca.setBounds(350, 40, ANCHOC, ALTOC);
        stock.setBounds(140, 100, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);

        buscar.setBounds(350, 10, ANCHOC, ALTOC);
        insertar.setBounds(450, 180, ANCHOC, ALTOC);
        actualizar.setBounds(10, 180, ANCHOC, ALTOC);
        eliminar.setBounds(150, 180, ANCHOC, ALTOC);
        limpiar.setBounds(300, 180, ANCHOC, ALTOC);
        resultados = new JTable();

        resultados = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        table.setBounds(10, 250, 600, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Codigo");
        tm.addColumn("Marca");
        tm.addColumn("Stock");
        tm.addColumn("Stock en Sucursal");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getCodigo(), fi.getTipo(), fi.getCantidad(), fi.getDisponibilidad()});
        }

        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
         
                Filtro f = new Filtro(codigo.getText(), Integer.parseInt(stock.getText()),true, marca.getSelectedItem().toString(), nombre.getText(), Integer.parseInt(precio.getText()));

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con existo");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                 Filtro f = new Filtro(codigo.getText(), Integer.parseInt(stock.getText()),true, marca.getSelectedItem().toString(), nombre.getText(), Integer.parseInt(precio.getText()));

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro Modificacion con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro");

                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(codigo.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro Eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(codigo.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El filtro buscado no se ha encontrado");
                } else {
                    codigo.setText(f.getCodigo());
                    marca.setSelectedItem(f.getTipo());
                    stock.setText(Integer.toString(f.getCantidad()));

                    if (f.getDisponibilidad()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        resultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    codigo.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
                    marca.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
                    stock.setText(resultados.getValueAt(resultados.getSelectedRow(), 2).toString());
                    if (resultados.getValueAt(resultados.getSelectedRow(), 3).toString() == "false") {
                        no.setSelected(true);
                    } else {
                        si.setSelected(true);
                    }
                }
            }
        });
    }

    public void limpiarCampos() {
        codigo.setText("");
        marca.setSelectedItem("");
        stock.setText("");
        precio.setText("");
        nombre.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new vista().setVisible(true);
            }
        });
    }
}
