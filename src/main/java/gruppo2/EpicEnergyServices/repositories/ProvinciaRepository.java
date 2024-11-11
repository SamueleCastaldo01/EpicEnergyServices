package gruppo2.EpicEnergyServices.repositories;

import gruppo2.EpicEnergyServices.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
}
