<%-- 
    Document   : vehicles
    Created on : 8/09/2025, 4:05:39 p. m.
    Author     : Andrea
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Lista de Vehículos</title>
    </head>
    <body>
        <h2>Agregar Vehículo</h2>
        <form action="vehicles" method="post">
            Placa: <input type="text" name="placa" /><br/>
            Marca: <input type="text" name="marca" /><br/>
            Modelo: <input type="text" name="modelo" /><br/>
            Color: <input type="text" name="color" /><br/>
            Propietario: <input type="text" name="propietario" /><br/>
            Año: <input type="number" name="anio" min="1900" max="2100" /><br/>
            <input type="submit" value="Agregar" />
        </form>

        <h2>Lista de Vehículos</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Placa</th>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Color</th>
                <th>Propietario</th>
                <th>Año</th>
            </tr>
            <c:forEach var="vehicle" items="${vehicles}">
                <tr>
                    <td>${vehicle.id}</td>
                    <td>${vehicle.placa}</td>
                    <td>${vehicle.marca}</td>
                    <td>${vehicle.modelo}</td>
                    <td>${vehicle.color}</td>
                    <td>${vehicle.propietario}</td>
                    <td>${vehicle.anio}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
