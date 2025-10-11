/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * DAO para la gestión de vehículos en la base de datos.
 * Todas las operaciones CRUD y de consulta deben manejar excepciones
 * y documentar los errores detectados.
 */
package com.garaje.persistence;

import com.garaje.model.Vehiculo;
import java.sql.*;
import java.util.*;

/**
 * VehiculoDAO realiza operaciones CRUD sobre la tabla vehiculos.
 */
public class VehiculoDAO {

    private final Connection con;

    /**
     * Inicializa con una conexión JDBC ya creada.
     *
     * @param con conexión activa a la BD
     */
    public VehiculoDAO(Connection con) {
        this.con = con;
    }

    /**
     * Lista todos los vehículos.
     */
    public List<Vehiculo> listar() throws SQLException {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapVehiculo(rs));
            }
        }
        return lista;
    }

    /**
     * Busca un vehículo por ID.
     */
    public Vehiculo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM vehiculos WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapVehiculo(rs);
                }
            }
        }
        return null;
    }

    /**
     * Busca un vehículo por placa.
     * Útil para validar duplicados.
     */
    public Vehiculo buscarPorPlaca(String placa) throws SQLException {
        String sql = "SELECT * FROM vehiculos WHERE placa=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapVehiculo(rs);
                }
            }
        }
        return null;
    }

    /**
     * Verifica si existe una placa en la BD (versión booleana).
     */
    public boolean existePlaca(String placa) throws SQLException {
        String sql = "SELECT COUNT(*) FROM vehiculos WHERE placa=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    /**
     * Agrega un nuevo vehículo.
     */
    public void agregar(Vehiculo v) throws SQLException {
        String sql = "INSERT INTO vehiculos (placa, marca, modelo, color, propietario, anio) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setString(4, v.getColor());
            ps.setString(5, v.getPropietario());
            ps.setInt(6, v.getAnio());
            ps.executeUpdate();
        }
    }

    /**
     * Actualiza un vehículo existente por ID.
     */
    public void actualizar(Vehiculo v) throws SQLException {
        String sql = "UPDATE vehiculos SET placa=?, marca=?, modelo=?, color=?, propietario=?, anio=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setString(4, v.getColor());
            ps.setString(5, v.getPropietario());
            ps.setInt(6, v.getAnio());
            ps.setInt(7, v.getId());
            ps.executeUpdate();
        }
    }

    /**
     * Elimina un vehículo por ID.
     */
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM vehiculos WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Convierte un ResultSet en objeto Vehiculo.
     */
    private Vehiculo mapVehiculo(ResultSet rs) throws SQLException {
        return new Vehiculo(
                rs.getInt("id"),
                rs.getString("placa"),
                rs.getString("marca"),
                rs.getString("modelo"),
                rs.getString("color"),
                rs.getString("propietario"),
                rs.getInt("anio")
        );
    }
}
