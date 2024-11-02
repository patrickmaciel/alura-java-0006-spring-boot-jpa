package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.OptionalDouble;

public class Serie {
  private String titulo;
  private Integer totalTemporadas;
  private Double avaliacao;
  private Categoria genero;
  private String atores;
  private String poster;
  private String sinopse;

  public Serie (DadosSerie dadoSerie) {
    this.titulo = dadoSerie.titulo();
    this.totalTemporadas = dadoSerie.totalTemporadas();
    this.avaliacao = OptionalDouble.of(Double.valueOf(dadoSerie.avaliacao())).orElse(0.0);
    this.genero = Categoria.fromString(dadoSerie.genero().split(",")[0].trim());
    this.atores = dadoSerie.atores();
    this.poster = dadoSerie.poster();
    this.sinopse = dadoSerie.sinopse();
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Integer getTotalTemporadas() {
    return totalTemporadas;
  }

  public void setTotalTemporadas(Integer totalTemporadas) {
    this.totalTemporadas = totalTemporadas;
  }

  public Double getAvaliacao() {
    return avaliacao;
  }

  public void setAvaliacao(Double avaliacao) {
    this.avaliacao = avaliacao;
  }

  public Categoria getGenero() {
    return genero;
  }

  public void setGenero(Categoria genero) {
    this.genero = genero;
  }

  public String getAtores() {
    return atores;
  }

  public void setAtores(String atores) {
    this.atores = atores;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getSinopse() {
    return sinopse;
  }

  public void setSinopse(String sinopse) {
    this.sinopse = sinopse;
  }

  @Override
  public String toString() {
    return "genero=" + genero +
        ", titulo='" + titulo + '\'' +
        ", totalTemporadas=" + totalTemporadas +
        ", avaliacao=" + avaliacao +
        ", genero=" + genero +
        ", atores='" + atores + '\'' +
        ", poster='" + poster + '\'' +
        ", sinopse='" + sinopse + '\'';
  }
}
