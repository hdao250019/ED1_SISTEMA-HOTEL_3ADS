package Controlador;

import Modelos.Habitacion;
import Modelos.HabitacionBD;
import Vistas.FrmGestorHab;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Jared
 */
public class GestionHabController implements ActionListener {

    private FrmGestorHab hab;
    private HabitacionBD habbd;
    private JTable tabla;
    private int idSeleccionado = -1;

    // Constructor
    public GestionHabController(FrmGestorHab hab, HabitacionBD habbd) {
        this.hab = hab;
        this.habbd = habbd;
        
        
        // Registrar ActionListeners
        if (this.hab.BTN_Act != null) this.hab.BTN_Act.addActionListener(this);
        if (this.hab.BTN_Eli != null) this.hab.BTN_Eli.addActionListener(this);
        if (this.hab.BTN_Lim != null) this.hab.BTN_Lim.addActionListener(this);

        // Cargar tabla inicial
        mostrarHab();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.hab != null) {
            if (e.getSource() == hab.BTN_Act) {
                actualizarHabitacion();
            } else if (e.getSource() == hab.BTN_Eli) {
                eliminarHabitacion();
            } else if (e.getSource() == hab.BTN_Lim) {
                limpiarCampos();
            }
        }
    }

    // Método para rellenar la tabla y asignarle el listener de selección
    public void mostrarHab() {
        DefaultTableModel modelo = new DefaultTableModel() {
            
        };

        modelo.addColumn("ID");
        modelo.addColumn("Num Habitacion");
        modelo.addColumn("Huespedes");
        modelo.addColumn("Tipo de Habitacion");
        modelo.addColumn("Disponibilidad Servicios");
        modelo.addColumn("Estado");

        List<Habitacion> listaHab = habbd.consultarHabitaciones();

        if (listaHab != null) {
            for (Habitacion habitacion : listaHab) {
                Object[] fila = {
                    habitacion.getId(),
                    habitacion.getNumHab(),
                    habitacion.getNumMaxHuesped(),
                    habitacion.getTipoHab(),
                    habitacion.getDispHab(),
                    habitacion.isEstado() ? "Disponible" : "Ocupada"
                };
                modelo.addRow(fila);
            }
        }

        tabla = new JTable(modelo);
        hab.SCR_Habi.setViewportView(tabla);
        
        // Listener para capturar el clic en la fila
        seleccionTabla();
    }

    // Detectar selección en la tabla
    private void seleccionTabla() {
        if (this.tabla != null) {
            this.tabla.getSelectionModel().addListSelectionListener(evento -> {
                if (!evento.getValueIsAdjusting()) {
                    cargarHab();
                }
            });
        }
    }

    // Cargar datos de la fila seleccionada en los campos
    private void cargarHab() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            return;
        }

        idSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
        
        // Cargar en los campos correspondientes
        if (hab.TXT_NumH != null) hab.TXT_NumH.setText(tabla.getValueAt(fila, 1).toString());
        if (hab.TXT_MaxH != null) hab.TXT_MaxH.setText(tabla.getValueAt(fila, 2).toString());
        if (hab.CBX_TipoH != null) hab.CBX_TipoH.setSelectedItem(tabla.getValueAt(fila, 3).toString());
        if (hab.CBX_DispS != null) hab.CBX_DispS.setSelectedItem(tabla.getValueAt(fila, 4).toString());
        
        if (hab.CHK_Disp != null) {
            String estadoStr = tabla.getValueAt(fila, 5).toString();
            hab.CHK_Disp.setSelected(estadoStr.equalsIgnoreCase("Disponible") || estadoStr.equalsIgnoreCase("true"));
        }
    }

    // Actualizar registro
    private void actualizarHabitacion() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(hab, "Por favor, selecciona una habitación de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int numHab = Integer.parseInt(hab.TXT_NumH.getText().trim());
            int maxHues = Integer.parseInt(hab.TXT_MaxH.getText().trim());
            String tipo = hab.CBX_TipoH.getSelectedItem().toString().trim();
            String dispS = hab.CBX_DispS.getSelectedItem().toString().trim();
            boolean estado = hab.CHK_Disp.isSelected();

            Habitacion habitacion = new Habitacion(idSeleccionado, numHab, maxHues, tipo, dispS, estado);
            boolean actualizada = habbd.actualizar(habitacion);

            if (actualizada) {
                JOptionPane.showMessageDialog(hab, "Habitación actualizada exitosamente.");
                mostrarHab();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(hab, "Error al actualizar la habitación.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(hab, "Por favor, ingrese valores numéricos válidos en Número y Huéspedes.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Eliminar registro
    public void eliminarHabitacion() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(hab, "Por favor, selecciona una habitación de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(hab,
                "¿Estás seguro de que deseas eliminar esta habitación?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminada = habbd.eliminar(idSeleccionado);
            if (eliminada) {
                JOptionPane.showMessageDialog(hab, "Habitación eliminada exitosamente.");
                mostrarHab();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(hab, "Error al eliminar la habitación.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Limpiar formulario
    private void limpiarCampos() {
        idSeleccionado = -1;
        if (hab.TXT_NumH != null) hab.TXT_NumH.setText("");
        if (hab.TXT_MaxH != null) hab.TXT_MaxH.setText("");
        if (hab.CBX_TipoH != null && hab.CBX_TipoH.getItemCount() > 0) hab.CBX_TipoH.setSelectedIndex(0);
        if (hab.CBX_DispS != null && hab.CBX_DispS.getItemCount() > 0) hab.CBX_DispS.setSelectedIndex(0);
        if (hab.CHK_Disp != null) hab.CHK_Disp.setSelected(false);
        if (tabla != null) tabla.clearSelection();
    }
}