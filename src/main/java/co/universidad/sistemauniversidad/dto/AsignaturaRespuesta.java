package co.universidad.sistemauniversidad.dto;

import java.util.List;

public class AsignaturaRespuesta {

  List<AsignaturaDTO> contenido;

  private int numeroPagina;
  private int medidaPagina;
  private long totalElementos;
  private int totalPaginas;
  private boolean ultimas;

  public AsignaturaRespuesta(){

  }

  public List<AsignaturaDTO> getContenido() {
    return this.contenido;
  }

  public void setContenido(List<AsignaturaDTO> contenido) {
    this.contenido = contenido;
  }

  public int getNumeroPagina() {
    return this.numeroPagina;
  }

  public void setNumeroPagina(int numeroPagina) {
    this.numeroPagina = numeroPagina;
  }

  public int getMedidaPagina() {
    return this.medidaPagina;
  }

  public void setMedidaPagina(int medidaPagina) {
    this.medidaPagina = medidaPagina;
  }

  public long getTotalElementos() {
    return this.totalElementos;
  }

  public void setTotalElementos(long totalElementos) {
    this.totalElementos = totalElementos;
  }

  public int getTotalPaginas() {
    return this.totalPaginas;
  }

  public void setTotalPaginas(int totalPaginas) {
    this.totalPaginas = totalPaginas;
  }

  public boolean isUltimas() {
    return this.ultimas;
  }

  public boolean getUltimas() {
    return this.ultimas;
  }

  public void setUltimas(boolean ultimas) {
    this.ultimas = ultimas;
  }

  
}
