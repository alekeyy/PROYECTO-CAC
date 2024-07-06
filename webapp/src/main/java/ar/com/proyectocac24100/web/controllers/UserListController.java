package ar.com.proyectocac24100.web.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.proyectocac24100.web.domain.Usuarios;
import ar.com.proyectocac24100.web.service.AppService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserListController")
public class UserListController extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //crear service
        AppService service = new AppService();

        //invocar metoso listarUsuarios > listar
        ArrayList<Usuarios> usuarios = service.listarUsuario();

        ObjectMapper mapper = new ObjectMapper();

        //convierto Objeto java a json string
        //ahora responde al front, Convirtiendo al nuevo Usuarios a json
        String usuariosJSON = mapper.writeValueAsString(usuarios);

        //mostrar por consola|enviar al frontend
        // Configurar cabeceras CORS
        resp.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
        resp.setHeader("Access-Control-Allow-Methods", "*"); // Métodos permitidos
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Cabeceras permitidas

        resp.getWriter().println(usuariosJSON);
    }   

}
