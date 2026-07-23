/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Angel H
 */
public class Usuario {
    private String nombre, mail, pass, rol;

    public Usuario(String nombre, String mail, String pass, String rol) {
        this.nombre = nombre;
        this.mail = mail;
        this.pass = pass;
        this.rol = rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.mail = mail;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public boolean validarLogin(String correoLogin, String passLogin){
        return mail.equals(correoLogin) && pass.equals(passLogin);
    }
}
