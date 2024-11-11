package gruppo2.EpicEnergyServices.repositories;

import gruppo2.EpicEnergyServices.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
}
