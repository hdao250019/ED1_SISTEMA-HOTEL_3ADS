/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import Controlador.*;
import Vistas.*;
import Modelos.*;
import javax.swing.JFrame;

/**
 *
 * @author Angel H
 */
public class Main {
    public static void main(String[] args) {
        
        //FrmNuevoU ventana = new FrmNuevoUsuario();
        FrmHabitaciones menu = new FrmHabitaciones();
        UsuarioDB usuariobd = new UsuarioDB();
        FrmGestorHab verHab = new FrmGestorHab();
        HabitacionBD habitacionbd = new HabitacionBD();
        
        FrmInicioSesion loginView = new FrmInicioSesion();
        UsuarioController control = new UsuarioController(loginView, usuariobd, menu, verHab);
        GestionHabController controller = new GestionHabController(verHab, habitacionbd);
        HabitacionControlles habitacion = new HabitacionControlles(menu, habitacionbd, verHab);
        loginView.setVisible(true);
    }
}
