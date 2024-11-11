package com.aluracursos.ScreenMatchSpring.repository;

import com.aluracursos.ScreenMatchSpring.model.Categoria;
import com.aluracursos.ScreenMatchSpring.model.Episodio;
import com.aluracursos.ScreenMatchSpring.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);

    List<Serie> findTop5ByOrderByEvaluacionesDesc();
    List<Serie> findByGenero(Categoria categoria);
/*
    List<Serie> findByTotalDeTemporadasLessThanEqualAndEvaluacionesGreaterThanEqual(int totalDeTemporadas, Double evaluaciones);
*/
    @Query("SELECT s FROM Serie s WHERE s.totalDeTemporadas <= :totalDeTemporadas AND s.evaluaciones >= :evaluaciones")
    List<Serie> seriesPorTemporadasYEvaluacion(int totalDeTemporadas, Double evaluaciones);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> episodiosPorNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5")
    List<Episodio> top5Episodios(Serie serie);
}
