package co.universidad.sistemauniversidad.dto;

// @author eosorio
import java.util.List;

public class AlumnoRespuesta {

    private List<AlumnoDTO> contenido;

    private int numeroPagina;
    private int medidaPagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultimas;

    public AlumnoRespuesta() {
    }

    public List<AlumnoDTO> getContenido() {
        return contenido;
    }

    public void setContenido(List<AlumnoDTO> contenido) {
        this.contenido = contenido;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getMedidaPagina() {
        return medidaPagina;
    }

    public void setMedidaPagina(int medidaPagina) {
        this.medidaPagina = medidaPagina;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public boolean isUltimas() {
        return ultimas;
    }

    public void setUltimas(boolean ultimas) {
        this.ultimas = ultimas;
    }
    
    

}
