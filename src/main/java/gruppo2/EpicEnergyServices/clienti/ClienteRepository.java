package gruppo2.EpicEnergyServices.clienti;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    Page<Cliente> findAllByOrderByNomeContattoAsc(Pageable pageable);
    Page<Cliente> findAllByOrderByFatturatoAnnualeAsc(Pageable pageable);
    Page<Cliente> findAllByOrderByDataInserimentoDesc(Pageable pageable);
    Page<Cliente> findAllByOrderByDataUltimoContattoDesc(Pageable pageable);
    Page<Cliente> findByFatturatoAnnualeBetween(BigDecimal minFatturato, BigDecimal maxFatturato, Pageable pageable);
    List<Cliente> findByDataInserimento(LocalDate dataInserimento);

}
