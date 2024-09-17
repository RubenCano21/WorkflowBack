package uagrm.bo.workflow.entidades;

import jakarta.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCi() {
        return ci;
    }

    public void setCi(Long ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Recepcionista(Long id, Long ci, String nombre, String apellido, String direccion, char sexo, Long telefono, String cargo) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.sexo = sexo;
        this.telefono = telefono;
        this.cargo = cargo;
    }

    public Recepcionista(Long ci, String nombre, String apellido, String direccion, char sexo, Long telefono, String cargo) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.sexo = sexo;
        this.telefono = telefono;
        this.cargo = cargo;
    }

    public Recepcionista() {
    }
}
