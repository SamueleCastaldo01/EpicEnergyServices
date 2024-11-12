package gruppo2.EpicEnergyServices.fatture;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    List<Fattura> findByClienteId(Long clienteId);
}
