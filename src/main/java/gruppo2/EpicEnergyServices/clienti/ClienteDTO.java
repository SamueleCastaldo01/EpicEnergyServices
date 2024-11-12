package gruppo2.EpicEnergyServices.clienti;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClienteDTO(
        Long id,
        String ragioneSociale,
        String partitaIva,
        String email,
        LocalDate dataInserimento,
        LocalDate dataUltimoContatto,
        BigDecimal fatturatoAnnuale,
        String pec,
        String telefono,
        String emailContatto,
        String nomeContatto,
        String cognomeContatto,
        String telefonoContatto,
        String logoAziendale,
        TipoCliente tipoCliente
) {}
