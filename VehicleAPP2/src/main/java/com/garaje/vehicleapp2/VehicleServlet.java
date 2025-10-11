
package com.garaje.vehicleapp2;

import com.garaje.facade.VehiculoFacade;
import com.garaje.model.Vehiculo;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/vehicles")
public class VehicleServlet extends HttpServlet {

    @EJB
    private VehiculoFacade vehiculoFacade; // Inyección automática del EJB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Vehiculo> vehicles = vehiculoFacade.listar();
            request.setAttribute("vehicles", vehicles);
            request.getRequestDispatcher("/vehicles.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener la lista de vehículos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Vehiculo v = new Vehiculo();
        v.setPlaca(request.getParameter("placa"));
        v.setMarca(request.getParameter("marca"));
        v.setModelo(request.getParameter("modelo"));
        v.setColor(request.getParameter("color"));
        v.setPropietario(request.getParameter("propietario"));

        // 🔹 Nuevo: capturamos el campo anio
        String anioStr = request.getParameter("anio");
        if (anioStr != null && !anioStr.isEmpty()) {
            v.setAnio(Integer.parseInt(anioStr));
        }

        try {
            vehiculoFacade.agregar(v);
            response.sendRedirect("vehicles"); // recarga la lista
        } catch (SQLException e) {
            throw new ServletException("Error al guardar el vehículo", e);
        }
    }
}
