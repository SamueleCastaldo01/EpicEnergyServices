package gruppo2.EpicEnergyServices.clienti;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ClienteRepository extends JpaRepository<gruppo2.EpicEnergyServices.clienti.Cliente, Long> {

    Page<gruppo2.EpicEnergyServices.clienti.Cliente> findByFatturatoAnnuale(BigDecimal fatturatoAnnuale, Pageable pageable);
    Page<gruppo2.EpicEnergyServices.clienti.Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);
    Page<gruppo2.EpicEnergyServices.clienti.Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);
    Page<gruppo2.EpicEnergyServices.clienti.Cliente> findByRagioneSocialeContainingIgnoreCase(String ragioneSociale, Pageable pageable);

    Page<gruppo2.EpicEnergyServices.clienti.Cliente> findBySedeLegaleComuneProvinciaNome(String provincia, Pageable pageable);
}
