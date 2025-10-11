/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.garaje.facade;

/**
 * Excepción personalizada para las reglas de negocio
 * del sistema de gestión de vehículos.
 */
public class BusinessException extends Exception {

    /**
     * Constructor con mensaje de error.
     * @param message descripción del error de negocio
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje de error y causa original.
     * @param message descripción del error de negocio
     * @param cause excepción original que causó el error
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
