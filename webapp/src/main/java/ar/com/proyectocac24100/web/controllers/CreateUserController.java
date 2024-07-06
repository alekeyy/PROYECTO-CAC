package ar.com.proyectocac24100.web.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.proyectocac24100.web.dto.UsuarioDTO;
import ar.com.proyectocac24100.web.service.AppService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CreateUserController")
public class CreateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonQueMandaElFront = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        System.out.println(jsonQueMandaElFront);

        ObjectMapper mapper = new ObjectMapper();

        // Crear el DTO
        UsuarioDTO dto = mapper.readValue(jsonQueMandaElFront, UsuarioDTO.class);

        AppService service = new AppService();
        service.crearUsuario(dto);

        // Responder algo al front con JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_CREATED); // creado

        String jsonResponse = "{\"message\":\"User created successfully\"}";
        resp.getWriter().write(jsonResponse);
    }
}
