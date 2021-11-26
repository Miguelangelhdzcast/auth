package com.proyectoestadias.lototresauth.entity;

public class Actividad {
     private  String nombre;
     private int dia;
     private int mes;

     public Actividad(){

     }

     public Actividad(String nombre, int dia, int mes) {
          this.nombre = nombre;
          this.dia = dia;
          this.mes = mes;
     }

     public String getNombre() {
          return nombre;
     }

     public void setNombre(String nombre) {
          this.nombre = nombre;
     }

     public int getDia() {
          return dia;
     }

     public void setDia(int dia) {
          this.dia = dia;
     }

     public int getMes() {
          return mes;
     }

     public void setMes(int mes) {
          this.mes = mes;
     }
}
