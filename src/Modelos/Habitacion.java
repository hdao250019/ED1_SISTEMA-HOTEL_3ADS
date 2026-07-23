/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Jared
 */
public class Habitacion {
    
    int id;
    int numHab;
    int numMaxHuesped;
    String TipoHab;
    String DispHab;
    boolean estado;

    public Habitacion() {
    }

    public Habitacion(int numHab, int numMaxHuesped, String TipoHab, String DispHab, boolean estado) {
        this.numHab = numHab;
        this.numMaxHuesped = numMaxHuesped;
        this.TipoHab = TipoHab;
        this.DispHab = DispHab;
        this.estado = estado;
    }

    public Habitacion(int id, int numHab, int numMaxHuesped, String TipoHab, String DispHab, boolean estado) {
        this.id = id;
        this.numHab = numHab;
        this.numMaxHuesped = numMaxHuesped;
        this.TipoHab = TipoHab;
        this.DispHab = DispHab;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumHab() {
        return numHab;
    }

    public void setNumHab(int numHab) {
        this.numHab = numHab;
    }

    public int getNumMaxHuesped() {
        return numMaxHuesped;
    }

    public void setNumMaxHuesped(int numMaxHuesped) {
        this.numMaxHuesped = numMaxHuesped;
    }

    public String getTipoHab() {
        return TipoHab;
    }

    public void setTipoHab(String TipoHab) {
        this.TipoHab = TipoHab;
    }

    public String getDispHab() {
        return DispHab;
    }

    public void setDispHab(String DispHab) {
        this.DispHab = DispHab;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
