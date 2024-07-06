package ar.com.proyectocac24100.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.com.proyectocac24100.web.domain.Usuarios;
import ar.com.proyectocac24100.web.dto.UsuarioDTO;

public class MySQLImpl implements ICrud{
    @Override
    public Usuarios getElementById(Long id) {
        String sql = "SELECT id,  nombre, email, telefono, comentario FROM cliente WHERE id = ?";

            //1.- necesito conectarme a la db
            Usuarios usuario = null;
            Connection con = null;
            try {
                con = ConnectionAdmin.conectar();

                PreparedStatement statement = con.prepareStatement(sql);
                statement.setLong(1,id);

                ResultSet resultset = statement.executeQuery();

                //leer el resultset y crear mis entidades
                //si hay datos, solo existe un registro!!!
                if (resultset.next()) {
                    Long idUsuario = resultset.getLong(1);
                    String nombre = resultset.getNString(2);
                    String email = resultset.getNString(3);                  
                    String telefono = resultset.getString(4);
                    String comentario = resultset.getString(5);
                    
                    usuario = new Usuarios(idUsuario, nombre, email, telefono, comentario);
                }

            } catch (SQLException e) {
                System.err.println(e);
            } finally {
                ConnectionAdmin.desconectar(con);
            }

        return usuario;
    }

    @Override
    public void crearUsuario(UsuarioDTO dto) {
        String sql = "INSERT INTO cliente (nombre, email,  telefono, comentario) values(?,?,?,?)";
       // String sql = "INSERT INTO usuarios (email, nombre, apellido) VALUES (?, ?, ?)";

        Connection connection = ConnectionAdmin.conectar();
        try {           
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, dto.getNombre());
            pst.setString(2, dto.getEmail());            
            pst.setString(3, dto.getTelefono());
            pst.setString(4, dto.getComentario());

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Un nuevo usuario fue insertado exitosamente!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            ConnectionAdmin.desconectar(connection);
        }        
    }

    //implementamos delete
    @Override
    public void delete(Long pk) {
        Connection cnx = null;
        String sql = "DELETE FROM cliente where id = ?";
        try {
            cnx = ConnectionAdmin.conectar();
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setLong(1, pk);
            if(pstmt.executeUpdate() > 0) {
                System.out.println("El susario con ID=" + pk + " se ha eliminado correctamente");
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            ConnectionAdmin.desconectar(cnx);
        }
    }

    //implementamos findAll
    @Override
    public ArrayList<Usuarios> findAll() {
        String sql = "SELECT * FROM cliente";
        ArrayList<Usuarios> usuarios = new ArrayList<>();

        //1.- necesito conectarme a la db
        Connection con = null;
        try {
            con = ConnectionAdmin.conectar();
            PreparedStatement statement = con.prepareStatement(sql);
            // statement.setLong(1,id);

            ResultSet resultset = statement.executeQuery();

            //leer el resultset y crear mis entidades
            //si hay datos, solo existe un registro!!!
            while (resultset.next()) {
                Long idCliente = resultset.getLong(1);
                String nombre = resultset.getString(2);
                String email = resultset.getString(3);
                String telefono = resultset.getNString(4);
                String comentario = resultset.getString(5);
                

                Usuarios usuario = new Usuarios(idCliente, nombre, email, telefono, comentario);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            ConnectionAdmin.desconectar(con);
        }

        return usuarios;
    }

    //implementamos Update    
    @Override
    public void update(UsuarioDTO usuario) {
      StringBuilder columnaSet = new StringBuilder();
      boolean hasPrev = false; // Para manejar las comas correctamente

     // Construir la cadena de columna SET dinámicamente
        if (usuario.getNombre() != null) {
                if (hasPrev) columnaSet.append(", ");
                columnaSet.append("nombre = ?");
                hasPrev = true;
            }
        if (usuario.getEmail() != null) {
            columnaSet.append("email = ?");
            hasPrev = true;
        }    
        if (usuario.getTelefono() != null) {
            if (hasPrev) columnaSet.append(", ");
            columnaSet.append("telefono = ?");
        }
        if (usuario.getComentario() != null) {
            if (hasPrev) columnaSet.append(", ");
            columnaSet.append("comentario = ?");
        }

        // SQL para actualizar el usuario
        String sql = "UPDATE cliente SET " + columnaSet.toString() + " WHERE id = ?";

        Connection con = null;
        try {
            con = ConnectionAdmin.conectar();

            // Preparar la declaración SQL
            PreparedStatement statement = con.prepareStatement(sql);
            int index = 1;

            // Establecer los valores de los parámetros en la declaración SQL
            if (usuario.getNombre() != null) {
                statement.setString(index++, usuario.getNombre());
            }            
            if (usuario.getEmail() != null) {
                statement.setString(index++, usuario.getEmail());
            }          
            if (usuario.getTelefono() != null) {
                statement.setString(index++, usuario.getTelefono());
            }
            if (usuario.getComentario() != null) {
                statement.setString(index++, usuario.getComentario());
            }

            statement.setLong(index, usuario.getId());

            // Ejecutar la actualización
            int rowsUpdated = statement.executeUpdate();

            // Confirmar la actualización
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el usuario con el ID proporcionado.");
            }

        } catch (SQLException e) {
            // Manejo de excepciones
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        } finally {
            // Desconectar la conexión
            ConnectionAdmin.desconectar(con);
        }
    }

}
