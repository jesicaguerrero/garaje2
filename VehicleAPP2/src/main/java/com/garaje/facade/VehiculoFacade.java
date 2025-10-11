/**
 * Fachada que expone los métodos básicos.
 * Sólo incluye el paso directo (sin reglas), para que los estudiantes
 * implementen las reglas de negocio en esta clase.
 */
package com.garaje.facade;

import com.garaje.model.Vehiculo;
import com.garaje.persistence.VehiculoDAO;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;
import java.time.Year;

/**
 * Fachada para operaciones sobre vehículos. Se incluyen reglas de negocio antes
 * de llamar al DAO.
 */
@Stateless
public class VehiculoFacade {

    @Resource(lookup = "jdbc/garageDB")
    private DataSource ds;

    private static final List<String> COLORES_PERMITIDOS
            = Arrays.asList("rojo", "blanco", "negro", "azul", "gris");

    /**
     * Listar vehículos
     */
    public List<Vehiculo> listar() throws SQLException {
        try (Connection con = ds.getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);
            return dao.listar();
        }
    }

    /**
     * Buscar vehículo por ID
     */
    public Vehiculo buscarPorId(int id) throws SQLException {
        try (Connection con = ds.getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);
            return dao.buscarPorId(id);
        }
    }

    /**
     * Agregar vehículo con reglas de negocio
     */
    public void agregar(Vehiculo v) throws SQLException {
        validarVehiculo(v);

        try (Connection con = ds.getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);

            // No permitir placas duplicadas
            if (dao.buscarPorPlaca(v.getPlaca()) != null) {
                throw new IllegalArgumentException("Ya existe un vehículo con esa placa.");
            }

            // Si la marca es Ferrari → simular notificación
            if ("ferrari".equalsIgnoreCase(v.getMarca())) {
                System.out.println(" Notificación: Se ha registrado un Ferrari!");
            }

            dao.agregar(v);
        }
    }

    /**
     * Actualizar vehículo con validaciones
     */
    public void actualizar(Vehiculo v) throws SQLException {
        validarVehiculo(v);

        try (Connection con = ds.getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);

            // Validar que el vehículo exista
            if (dao.buscarPorId(v.getId()) == null) {
                throw new IllegalArgumentException("No se puede actualizar: el vehículo no existe.");
            }

            dao.actualizar(v);
        }
    }

    /**
     * Eliminar vehículo
     */
    public void eliminar(int id) throws SQLException {
        try (Connection con = ds.getConnection()) {
            VehiculoDAO dao = new VehiculoDAO(con);
            Vehiculo v = dao.buscarPorId(id);

            if (v == null) {
                throw new IllegalArgumentException("No existe vehículo con ese ID.");
            }

            // No eliminar si el propietario es Administrador
            if ("administrador".equalsIgnoreCase(v.getPropietario())) {
                throw new IllegalArgumentException("No se puede eliminar un vehículo del Administrador.");
            }

            dao.eliminar(id);
        }
    }

    /**
     * Método auxiliar para validar un vehículo antes de agregar/actualizar
     */
    private void validarVehiculo(Vehiculo v) {
        // Validar propietario
        if (v.getPropietario() == null || v.getPropietario().trim().length() < 5) {
            throw new IllegalArgumentException("El propietario no puede estar vacío y debe tener al menos 5 caracteres.");
        }

        // Validar longitud mínima de marca, modelo y placa
        if (v.getMarca() == null || v.getMarca().trim().length() < 3) {
            throw new IllegalArgumentException("La marca debe tener al menos 3 caracteres.");
        }
        if (v.getModelo() == null || v.getModelo().trim().length() < 3) {
            throw new IllegalArgumentException("El modelo debe tener al menos 3 caracteres.");
        }
        if (v.getPlaca() == null || v.getPlaca().trim().length() < 3) {
            throw new IllegalArgumentException("La placa debe tener al menos 3 caracteres.");
        }

        // Validar color permitido
        if (!COLORES_PERMITIDOS.contains(v.getColor().toLowerCase())) {
            throw new IllegalArgumentException("El color no es válido. Debe ser: " + COLORES_PERMITIDOS);
        }

        // Validar antigüedad del vehículo (20 años)
        int anioActual = Year.now().getValue();
        if (v.getAnio() < (anioActual - 20)) {
            throw new IllegalArgumentException("El vehículo no puede tener más de 20 años de antigüedad.");
        }

        // Simulación de validación anti-SQL Injection
        if (contieneSQLInjection(v)) {
            throw new IllegalArgumentException("Datos inválidos: posible intento de inyección SQL.");
        }
    }

    /**
     * Valida posibles cadenas maliciosas (simulación)
     */
    private boolean contieneSQLInjection(Vehiculo v) {
        String patron = "(?i)(;|--|drop|delete|insert|update|select|exec)";
        return v.getMarca().matches(patron)
                || v.getModelo().matches(patron)
                || v.getPlaca().matches(patron)
                || v.getPropietario().matches(patron);
    }
}
