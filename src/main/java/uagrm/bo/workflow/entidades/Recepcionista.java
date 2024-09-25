package uagrm.bo.workflow.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Recepcionista")
public class Recepcionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="ci")
    private Long ci;
    @Column(name="nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name="apellido", nullable = false, length = 50)
    private String apellido;
    @Column(name="direccion", nullable = false, length = 50)
    private String direccion;
    @Column(name="sexo")
    private char sexo;
    @Column(name="telefono")
    private Long telefono;
    @Column(name="cargo", nullable = false, length = 50)
    private String cargo;

}
