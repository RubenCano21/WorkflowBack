package uagrm.bo.workflow.entidades;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 1, max = 60, message = "El nombre debe tener entre 1 y 60 caracteres")
    private String nombre;

    @NotNull
    @Email(message = "Debe proporcionar un email valido")
    private String email;

    @Column(nullable = false)
    private String password;

//    @Transient
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // solo lectura
//    private boolean admin;

    @JsonIgnoreProperties({"users", "handler", "hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "role_id"})}
    )
    private List<Rol> roles = new ArrayList<>();

    public boolean isAdmin(){
        return roles.stream().anyMatch(rol -> rol.getERol().equals(ERol.ADMIN));
    }

    public boolean isRecepcionista(){
        return roles.stream().anyMatch(rol -> rol.getERol().equals(ERol.RECEPCIONISTA));
    }

}
