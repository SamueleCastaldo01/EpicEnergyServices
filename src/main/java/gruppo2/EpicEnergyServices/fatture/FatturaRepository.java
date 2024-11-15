package gruppo2.EpicEnergyServices.fatture;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {
    Page<Fattura> findByClienteId(Long clienteId, Pageable pageable);
    Page<Fattura> findByCliente_NomeContatto(String nomeContatto, Pageable pageable);
    Page<Fattura> findByStatoFatturaId(Long clienteId, Pageable pageable);
    Page<Fattura> findByStatoFattura_NomeStato(String nomeStato, Pageable pageable);
    Page<Fattura> findByDataBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    Page<Fattura> findByImportoBetween(double importoMin, double importoMax, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno")
    Page<Fattura> findByAnno(@Param("anno") int anno, Pageable pageable);


}
