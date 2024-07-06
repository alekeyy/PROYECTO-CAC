package ar.com.proyectocac24100.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.proyectocac24100.web.dto.UsuarioDTO;
import ar.com.proyectocac24100.web.service.AppService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet{
    //private long id = 1L;
    private AppService service;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new AppService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Leer el cuerpo de la solicitud
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String body = sb.toString();

        // Convertir el cuerpo JSON en un objeto UsuarioDTO
        UsuarioDTO usuario = objectMapper.readValue(body, UsuarioDTO.class);

        // Actualizar el usuario utilizando el servicio
        boolean actualizado = service.actualizarUsuario(usuario);

        // Enviar la respuesta
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (actualizado) {
            resp.getWriter().write("{\"message\": \"Usuario actualizado correctamente\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\": \"Error al actualizar el usuario\"}");
        }
    }
}
