package ar.com.proyectocac24100.web.dto;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;    
    private String comentario;

    //necesitamos un constructor por defecto para poder usar jackson
    public UsuarioDTO() {

    } 

    public UsuarioDTO(Long id, String nombre, String email, String telefono, String comentario) {
        this.comentario = comentario;
        this.email = email;
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Constructor para creación sin ID
    public UsuarioDTO(String nombre, String email, String telefono, String comentario) {
        this.comentario = comentario;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }



}
