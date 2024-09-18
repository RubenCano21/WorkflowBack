package uagrm.bo.workflow.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="usuarios_roles",
            joinColumns = @JoinColumn(
                    name="usuario_id", referencedColumnName = "id"
            ), inverseJoinColumns = @JoinColumn(name="rol_id", referencedColumnName = "id")
    )
    private Collection<Rol> roles;
}
