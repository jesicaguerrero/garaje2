/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.garaje.model;

    /**
     * Clase de modelo que representa un vehículo en el sistema.
     */
    public class Vehiculo {

        private int id;
        private String placa;
        private String marca;
        private String modelo;
        private String color;
        private String propietario;
        private int anio;

        /**
         * Constructor vacío (requerido para frameworks y serialización).
         */
        public Vehiculo() {
        }

        /**
         * Constructor completo.
         *
         * @param id identificador único del vehículo
         * @param placa placa del vehículo
         * @param marca marca del vehículo
         * @param modelo modelo del vehículo
         * @param color color del vehículo
         * @param propietario propietario del vehículo
         * @param anio año de fabricación del vehículo
         */
        public Vehiculo(int id, String placa, String marca, String modelo,
                String color, String propietario, int anio) {
            this.id = id;
            this.placa = placa;
            this.marca = marca;
            this.modelo = modelo;
            this.color = color;
            this.propietario = propietario;
            this.anio = anio;
        }

        /**
         * @return id del vehículo
         */
        public int getId() {
            return id;
        }

        /**
         * @param id establece el identificador único
         */
        public void setId(int id) {
            this.id = id;
        }

        /**
         * @return placa del vehículo
         */
        public String getPlaca() {
            return placa;
        }

        /**
         * @param placa establece la placa
         */
        public void setPlaca(String placa) {
            this.placa = placa;
        }

        /**
         * @return marca del vehículo
         */
        public String getMarca() {
            return marca;
        }

        /**
         * @param marca establece la marca
         */
        public void setMarca(String marca) {
            this.marca = marca;
        }

        /**
         * @return modelo del vehículo
         */
        public String getModelo() {
            return modelo;
        }

        /**
         * @param modelo establece el modelo
         */
        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        /**
         * @return color del vehículo
         */
        public String getColor() {
            return color;
        }

        /**
         * @param color establece el color
         */
        public void setColor(String color) {
            this.color = color;
        }

        /**
         * @return propietario del vehículo
         */
        public String getPropietario() {
            return propietario;
        }

        /**
         * @param propietario establece el propietario
         */
        public void setPropietario(String propietario) {
            this.propietario = propietario;
        }

        /**
         * @return año de fabricación del vehículo
         */
        public int getAnio() {
            return anio;
        }

        /**
         * @param anio establece el año de fabricación
         */
        public void setAnio(int anio) {
            this.anio = anio;
        }

        @Override
        public String toString() {
            return "Vehiculo{"
                    + "id=" + id
                    + ", placa='" + placa + '\''
                    + ", marca='" + marca + '\''
                    + ", modelo='" + modelo + '\''
                    + ", color='" + color + '\''
                    + ", propietario='" + propietario + '\''
                    + ", anio=" + anio
                    + '}';
        }
    }

