/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Angel H
 */
public class UsuarioController implements ActionListener{
    private FrmInicioSesion vistaLogin;
    private UsuarioBD usuariobd;
    private MainFrm menu;
    private int contadorLogin = 0;
    

    // Constructor
    public LoginController(FrmInicioSesion vistaLogin, UsuarioBD usuariobd, MainFrm menu) {
        this.vistaLogin = vistaLogin;
        this.usuariobd = usuariobd;
        this.menu = menu;
        
        this.vistaLogin.btnIni.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.btnIni) {
            validarAcceso();
        }
    }

    private void validarAcceso() {
        String usuario = vistaLogin.txtUsuario.getText().trim();
        String pass = new String (vistaLogin.txtPass.getPassword()).trim();

        // Validar campos vacíos
        if (usuario.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(vistaLogin, "Por favor, llene todos los campos.", 
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuarioValidado = this.usuariobd.login(usuario, pass);

        if (usuarioValidado != null) {
            
            SesionAct.setUsuarioActual(usuarioValidado);
            JOptionPane.showMessageDialog(vistaLogin, "¡Bienvenido de nuevo, " + usuarioValidado.getUsuario()+ "!");

            
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
            
            vistaLogin.dispose();
            
        } else {
            contadorLogin++; 
            JOptionPane.showMessageDialog(vistaLogin, "Credenciales Incorrectas");

            if (contadorLogin >= 3) {
                JOptionPane.showMessageDialog(vistaLogin, "Se alcanzó la cantidad máxima de intentos", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                vistaLogin.dispose(); 
            }
        }
    }
}
