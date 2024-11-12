package gruppo2.EpicEnergyServices.comune;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
    Comune findByComune(String comune);
}
