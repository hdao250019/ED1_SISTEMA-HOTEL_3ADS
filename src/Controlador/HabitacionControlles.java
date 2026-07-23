
package Controlador;

import Conection.HabitacionBD;
import Modelos.Habitacion;
import Modelos.HabitacionBD;
import Vistas.FrmGestorHab;
import Vistas.FrmHabitaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel H
 */
public class HabitacionControlles implements ActionListener {

    private FrmHabitaciones hab;
    private HabitacionBD habitacionbd;
    private FrmGestorHab verHab;
    
    public HabitacionControlles(FrmHabitaciones hab, HabitacionBD habitacionbd, FrmGestorHab verHab){
        this.hab = hab;
        this.habitacionbd = habitacionbd;
        this.verHab = verHab;
        
        // Verifica si el objeto de la ventana de registro existe
        if (this.hab != null) {
            // Verifica si el botón Crear Cuenta ya fue inicializado en los componentes
            if (this.hab.BTN_Ingresar != null) {
                this.hab.BTN_Ingresar.addActionListener(this);
            }
        }
    }
    
    
        
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    // Metodo para guardar Usuarios
    private void guardarHabitacion(){
        
        // Obtener los datos desde la ventana
        int numHab = Integer.parseInt(hab.TXT_NumH.getText());
        int maxHues = Integer.parseInt(hab.TXT_MaxH.getText());
        String tipo = hab.CBX_TipoH.getSelectedItem().toString().trim();
        String DispHab = hab.CBX_DispS.getSelectedItem().toString().trim();
        boolean isSeleccionado = hab.CHK_Disp.isSelected(); 

        // Guardamos el texto correspondiente en una variable
        String estado = isSeleccionado ? "Disponible" : "Ocupada";
        
        
        if (numHab <= 0 || maxHues < 0  || tipo.isEmpty() || DispHab.isEmpty()) {
            // Mostrar un mensaje de advertencia al usuario
            JOptionPane.showMessageDialog(hab, "Por favor, complete todos los campos.", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        Habitacion habi = new Habitacion(numHab, maxHues, tipo, DispHab, isSeleccionado );
        // Enviar el objeto de usuario antes de guardado para que el modelo lo reciba
        boolean resultado = habitacionbd.insertar(habi);
        
        // Evaluar si el resultado de la insercion fue exitoso
        if (resultado) {
            JOptionPane.showMessageDialog(hab, "Registro exitoso");
            LimpiarCampos();
            
            // Solo actualiza la tabla si la ventana existe
            if (this.verHab != null) {
                mostrarHab();
            }
        } else {
            JOptionPane.showMessageDialog(hab, "Error al registrar");
        }
    }
    
    // Metodo para crear la tabla mostrarCategorias
    private void mostrarHab(){
    
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Añadir columnas de la tabla
        modelo.addColumn("ID");
        modelo.addColumn("Num Habitacion");
        modelo.addColumn("Huespedes");
        modelo.addColumn("Tipo de Habitacion");
        modelo.addColumn("Disponibilidad de servicios");
        modelo.addColumn("Estado");
        
        List<Habitacion> listaCat = habitacionbd.consultarHabitaciones();
        
        // Ciclo para recorrer la lista de usuarios
        for(Habitacion habitacion : listaCat){
            
            // Guardar los objetos en un arreglo para usarlos en la tabla
            Object[] fila = {
                habitacion.getId(),
                habitacion.getNumHab(),
                habitacion.getNumMaxHuesped(),
                habitacion.getTipoHab(),
                habitacion.getDispHab(),
                habitacion.isEstado()
            };
            
            modelo.addRow(fila);
        }
        //Crear tabla
        JTable tablaUser = new JTable(modelo);
        // Usar JScrollpane para mostrar la tabla
        hab.SCR_Habi.setViewportView(tablaUser);
        
    }
    
    // Metodo para limpiar campos de Usuarios
    private void LimpiarCampos(){
        hab.TXT_NumH.setText("");
        hab.TXT_MaxH.setText("");
    }

    
}
