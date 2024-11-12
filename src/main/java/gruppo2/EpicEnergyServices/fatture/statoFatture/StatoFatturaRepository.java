package gruppo2.EpicEnergyServices.fatture.statoFatture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {
}
