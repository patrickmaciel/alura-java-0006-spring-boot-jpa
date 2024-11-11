package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.ConsultaChatGPT;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name="series")
public class Serie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String titulo;

  private Integer totalTemporadas;
  private Double avaliacao;

  @Enumerated(EnumType.STRING)
  private Categoria genero;

  private String atores;
  private String poster;
  private String sinopse;

  @OneToMany(mappedBy = "serie")
  private List<Episodio> episodios = new ArrayList<>();

  public Serie (DadosSerie dadoSerie) {
    this.titulo = dadoSerie.titulo();
    this.totalTemporadas = dadoSerie.totalTemporadas();
    this.avaliacao = OptionalDouble.of(Double.valueOf(dadoSerie.avaliacao())).orElse(0.0);
    this.genero = Categoria.fromString(dadoSerie.genero().split(",")[0].trim());
    this.atores = dadoSerie.atores();
    this.poster = dadoSerie.poster();
    setSinopse(ConsultaChatGPT.obterTraducao(dadoSerie.sinopse()).trim());
  }

  public Serie() {}

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
    if (sinopse != null && sinopse.length() > 255) {
      this.sinopse = sinopse.substring(0, 255);
    } else {
      this.sinopse = sinopse;
    }
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

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public List<Episodio> getEpisodios() {
    return episodios;
  }

  public void setEpisodios(List<Episodio> episodios) {
    this.episodios = episodios;
  }
}
