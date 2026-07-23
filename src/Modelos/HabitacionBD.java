/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Controlador.Conection;
import Modelos.Habitacion;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class HabitacionBD {
    
    public boolean insertar(Habitacion habitacion){
        String sql_query = "INSERT INTO habitacion(num_habitacion, max_huespedes, tipo, dispo_servicios, estado) VALUES(?, ?, ?, ?)";
        
        try{
            //Conexion a la BD
            Connection conn = Conection.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(sql_query);
            // Enviar los datos del modelo
            stmt.setInt(1, habitacion.getNumHab());
            stmt.setInt(2, habitacion.getNumMaxHuesped());
            stmt.setString(3, habitacion.getTipoHab());
            stmt.setString(4, habitacion.getDispHab());
            stmt.setBoolean(5, habitacion.isEstado());
            
            
            //Ejecutar el query en la DB
            stmt.executeUpdate();
            
            // Cerrar Statement y la conexion a la DB
            stmt.close();
            conn.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Error al insertar: "+e.getMessage());
            return false;
        }
    }

    //Metodo para consultar todas las Habitaciones
    public List<Habitacion> consultarHabitaciones(){
        List<Habitacion> listaCat = new ArrayList<>();
        String query_sql = "SELECT * FROM habitacion";
        try{
            //Conexion a la BD
            Connection conn = Conection.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            
            ResultSet result = stmt.executeQuery();
            
            // Ciclo para obtener todos los resgistros
            while(result.next()){
                int id = result.getInt("id_habitacion");
                int numHabi = result.getInt("num_habitacion");
                int NumMaxHues = result.getInt("max_huespedes");
                String tipoHab = result.getString("tipo");
                String DispSer = result.getString("dispo_servicios");
                Boolean Estado = result.getBoolean("estado");
                
                // crear objeto usuario y guardarlos en la lista
                Habitacion hab = new Habitacion(id, numHabi, NumMaxHues, tipoHab, DispSer, Estado);
                listaCat.add(hab);
            }
        }catch(SQLException e){
            System.out.println("Error en consulta: " + e.getMessage());
        }
        return listaCat;
    }

    public boolean actualizar(Habitacion habitacion){
        
        String query_sql = "UPDATE habitacion SET num_habitacion = ?, max_huespedes = ?, tipo = ?, dispo_servicios = ?, estado = ? WHERE id_habitacion = ?";
        
        try{
             //Conexion a la BD
            Connection conn = Conection.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql);
            // Enviar los datos del modelo
            stmt.setInt(1, habitacion.getNumHab());
            stmt.setInt(2, habitacion.getNumMaxHuesped());
            stmt.setString(3, habitacion.getTipoHab());
            stmt.setString(4, habitacion.getDispHab());
            stmt.setBoolean(5, habitacion.isEstado());
              
              // Verificar el numero de filas que cambiaron 
              int filas_cambiadas = stmt.executeUpdate();
              
              // Cuando es booean no es necesario aplicar un if
              return filas_cambiadas > 0;
              
            
            
        }catch(SQLException e){
            System.out.println("ERROR AL ACTUALIZAR EN LA BD" + e.getMessage());
            return false;
            
        }
    }

    // METODO PARA ELIMINAR Hbaitaciones
    public boolean eliminar(int id_habitacion){
            
        String query_sql = "DELETE FROM habitacion WHERE id_habitacion = ?";
            
        try{
            //Conexion a la BD
            Connection conn = Conection.conexion();
            //Crear el preparedstatement para mandarlo a la DB
            PreparedStatement stmt = conn.prepareStatement(query_sql); 
                
            stmt.setInt(1, id_habitacion);
            
            // Valor de las filas afectadas
            int filas_cambiadas = stmt.executeUpdate();
            return filas_cambiadas > 0; 
           
        }catch(SQLException e){
            System.out.println("ERROR AL BORRAR USUARIO EN LA BD: " + e.getMessage());
            return false;
        }
            
        }
}
