package gruppo2.EpicEnergyServices.comune;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
    Comune findByComune(String comune);
    List<Comune> findByProvincia_Provincia(String provincia);
}
