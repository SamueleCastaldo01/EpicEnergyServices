package gruppo2.EpicEnergyServices.clienti;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ClienteDTO(
        @NotNull(message = "ID non può essere nullo")
        Long id,

        @NotBlank(message = "Ragione Sociale è obbligatoria")
        @Size(max = 100, message = "La Ragione Sociale non può superare i 100 caratteri")
        String ragioneSociale,

        @NotBlank(message = "Partita IVA è obbligatoria")
        @Pattern(regexp = "^[0-9]{11}$", message = "La Partita IVA deve avere 11 numeri")
        String partitaIva,

        @Email(message = "Email non valida")
        String email,

        @NotNull(message = "Data di Inserimento è obbligatoria")
        LocalDate dataInserimento,

        @NotNull(message = "Data dell'ultimo contatto è obbligatoria")
        LocalDate dataUltimoContatto,

        @Positive(message = "Il fatturato annuale deve essere maggiore di zero")
        BigDecimal fatturatoAnnuale,

        @Email(message = "PEC non valida")
        String pec,

        @Pattern(regexp = "^\\+?[0-9]*$", message = "Telefono non valido")
        String telefono,

        @Email(message = "Email di contatto non valida")
        String emailContatto,

        @NotBlank(message = "Nome del contatto è obbligatorio")
        String nomeContatto,

        @NotBlank(message = "Cognome del contatto è obbligatorio")
        String cognomeContatto,

        @Pattern(regexp = "^\\+?[0-9]*$", message = "Telefono contatto non valido")
        String telefonoContatto,

        String logoAziendale,

        @NotNull(message = "Tipo cliente è obbligatorio")
        TipoCliente tipoCliente
) {}
