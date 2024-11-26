package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SerieRepository extends JpaRepository<Serie, Long> {
  Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

  List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

  List<Serie> findTop5ByOrderByAvaliacaoDesc();

  List<Serie> findByGenero(Categoria categoria);

  List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int numeroTemporadas, Double avaliacao);

  @Query(value = "SELECT * FROM series s WHERE s.total_temporadas <= :numeroTemporadas AND s.avaliacao >= :avaliacao", nativeQuery = true)
  List<Serie> seriesPorTemporadaEAvaliacao(int numeroTemporadas, Double avaliacao);

  // JPQL - Java Persistence Query Language
  @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :numeroTemporadas AND s.avaliacao >= :avaliacao")
  List<Serie> seriesPorTemporadaEAvaliacaoUsandoJPQL(int numeroTemporadas, Double avaliacao);

  @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trecho%")
  List<Episodio> buscaEpisodioPorTrecho(String trecho);

  @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
  List<Episodio> buscaTopEpisodiosPorSerie(Serie serie);

  @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :ano")
  List<Episodio> buscaEpisodioPorSerieEAno(Serie serie, int ano);
}
