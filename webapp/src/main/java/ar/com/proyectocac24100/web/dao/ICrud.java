package ar.com.proyectocac24100.web.dao;

import java.util.ArrayList;

import ar.com.proyectocac24100.web.domain.Usuarios;
import ar.com.proyectocac24100.web.dto.UsuarioDTO;

public interface ICrud {
    public Usuarios getElementById(Long id);

    public void crearUsuario(UsuarioDTO dto);
    
    public void delete(Long pk);

    public ArrayList<Usuarios> findAll();

    public void update(UsuarioDTO usuario);
}
