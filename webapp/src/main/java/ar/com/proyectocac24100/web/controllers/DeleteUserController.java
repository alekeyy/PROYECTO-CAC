package ar.com.proyectocac24100.web.controllers;

import java.io.IOException;

import ar.com.proyectocac24100.web.service.AppService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteUserController")
public class DeleteUserController extends HttpServlet {
    @Override
    protected void doDelete(
        HttpServletRequest req,
        HttpServletResponse resp
    ) throws ServletException, IOException {
        String id = req.getParameter("id");

        AppService service = new AppService();

        service.eliminarUsuarioPorId(Long.parseLong(id));
        
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}


