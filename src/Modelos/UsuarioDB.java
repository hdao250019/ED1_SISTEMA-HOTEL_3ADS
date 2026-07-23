/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Controlador.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angel H
 */
public class UsuarioDB {
    
    public Usuario login(String usuario, String contrasenia) {
        // Buscamos un registro que coincida con ambos datos
        String query_sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
    
    try {
        Connection conn = Conection.conexion();
        PreparedStatement stmt = conn.prepareStatement(query_sql);
        
        stmt.setString(1, usuario);
        stmt.setString(2, contrasenia);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            Usuario usuarioEncontrado = new Usuario(
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contrasena"),
                rs.getString("rol")
            );
            return usuarioEncontrado; 
        }
        
    } catch (SQLException e) {
        System.out.println("ERROR EN LOGIN BD: " + e.getMessage());
    }
    
    return null;
}
    
}
