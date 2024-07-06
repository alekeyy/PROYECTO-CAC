package ar.com.proyectocac24100.web.service;

import java.util.ArrayList;

import ar.com.proyectocac24100.web.dao.ICrud;
import ar.com.proyectocac24100.web.dao.MySQLImpl;
import ar.com.proyectocac24100.web.domain.Usuarios;
import ar.com.proyectocac24100.web.dto.UsuarioDTO;

public class AppService {
     private ICrud crud; //es nulo por no ser un primitivo

    public AppService() {
        //interface en este caso crud = implementacion
        crud = new MySQLImpl();
    }

    public void crearUsuario(UsuarioDTO dto) {
        this.crud.crearUsuario(dto);
    }

    public Usuarios obtenetUsuarioID(Long id) { 
        return this.crud.getElementById(id);
    }

    public void eliminarUsuarioPorId(Long id) {
        this.crud.delete(id);
    }

    public ArrayList<Usuarios> listarUsuario() {
       return this.crud.findAll();
    }

    public boolean actualizarUsuario(UsuarioDTO usuario) {
        this.crud.update(usuario);

        System.out.println("Actualizando usuario: " + usuario.getId());

        return true;
        
    }
}
