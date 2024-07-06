package ar.com.proyectocac24100.web.domain;

public class Usuarios {
    private Long id;
    private String nombre;
    private String email;    
    private String telefono;
    private String comentario;

     //constructor para traer/select desde la Db
    public Usuarios(Long id, String nombre, String email, String telefono, String comentario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.comentario = comentario;        
    }

     //constructor para crear/insert a la Db

    public Usuarios(String nombre, String email, String telefono, String comentario) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.comentario = comentario;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuarios{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", email=").append(email);
        sb.append(", telefono=").append(telefono);
        sb.append(", comentario=").append(comentario);
        sb.append('}');
        return sb.toString();
    }
}
