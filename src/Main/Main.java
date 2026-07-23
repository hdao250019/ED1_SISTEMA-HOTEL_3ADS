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
        FrmInicioSesion loginView = new FrmInicioSesion();
        //FrmNuevoU ventana = new FrmNuevoUsuario();
        JFrame menu = new JFrame();
        UsuarioDB usuariobd = new UsuarioDB();
        
        UsuarioController control = new UsuarioController(loginView, usuariobd, menu);
        loginView.setVisible(true);
    }
}
