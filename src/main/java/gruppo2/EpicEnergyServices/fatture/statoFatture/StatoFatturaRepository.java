package gruppo2.EpicEnergyServices.fatture.statoFatture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {

    @Query("SELECT s FROM StatoFattura s WHERE LOWER(s.stato) = LOWER(:stato)")
    Optional<StatoFattura> findByStato(@Param("stato") String stato);
}
