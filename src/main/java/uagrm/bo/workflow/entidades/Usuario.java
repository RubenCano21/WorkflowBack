package uagrm.bo.workflow.entidades;
import jakarta.persistence.*;
import java.util.Collection;

@Entity
@Table(name ="usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="usuarios_roles",
            joinColumns = @JoinColumn(
                    name="usuario_id", referencedColumnName = "id"
            ), inverseJoinColumns = @JoinColumn(name="rol_id", referencedColumnName = "id")
    )
    private Collection<Rol> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }

    public Usuario(Long id, String nombre, String email, String password, Collection<Rol> roles) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Usuario(String nombre, String email, String password, Collection<Rol> roles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Usuario() {
    }
}
