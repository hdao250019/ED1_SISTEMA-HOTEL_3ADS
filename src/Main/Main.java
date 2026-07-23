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
        
        FrmInicioSesion loginView = new FrmInicioSesion();
        UsuarioController control = new UsuarioController(loginView, usuariobd, menu);
        loginView.setVisible(true);
    }
}
