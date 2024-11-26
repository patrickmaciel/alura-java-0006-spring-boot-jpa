package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
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
}
