package co.universidad.sistemauniversidad.dto;

// @author eosorio
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class SemestreDTO {

    private long id;

    @NotEmpty
    @Size(min = 4, max = 20, message = "El nombre del semestre debe tener como minimo 4 caracteres y como maximo 20 caractres")
    private String nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
