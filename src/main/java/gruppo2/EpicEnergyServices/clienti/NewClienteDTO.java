package gruppo2.EpicEnergyServices.clienti;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewClienteDTO(

        String ragioneSociale ,
        long idSedeOperativa ,
        long idSedeLegale ,
        TipoCliente tipoCliente ,
        String logoAziendale ,
        String telefonoContatto,
        String cognomeContatto ,
        String nomeContatto ,
        String telefono ,
        String pec ,
        String emailContatto ,
        BigDecimal fatturatoAnnuale ,
        LocalDate dataInserimento ,
        LocalDate dataUltimoContatto ,
        String email ,
        String partitaIva
) {}
