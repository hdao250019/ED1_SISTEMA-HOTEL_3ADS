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

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public String getRol() {
        return rol;
    }

    
}
