package gruppo2.EpicEnergyServices.clienti;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewClienteDTO(

        @Size(max = 100, message = "La Ragione Sociale non può superare i 100 caratteri")
        String ragioneSociale,

        @Positive(message = "L'id della sede operativa deve essere positivo")
        long idSedeOperativa,

        @Positive(message = "L'id della sede legale deve essere positivo")
        long idSedeLegale,

        TipoCliente tipoCliente,

        String logoAziendale,

        @Pattern(regexp = "\\d{1,10}", message = "Il telefono di contatto deve essere un numero di 10 cifre")
        String telefonoContatto,

        @NotBlank(message = "Il cognome del contatto è obbligatorio")
        String cognomeContatto,

        @NotBlank(message = "Il nome del contatto è obbligatorio")
        String nomeContatto,

        @NotBlank(message = "Il telefono è obbligatorio")
        @Pattern(regexp = "\\d{1,10}", message = "Il telefono deve essere un numero di 10 cifre")
        String telefono,

        @NotBlank(message = "PEC è obbligatoria")
        @Email(message = "PEC non valida")
        String pec,

        @Email(message = "Email di contatto non valida")
        String emailContatto,

        @Positive(message = "Il fatturato annuale deve essere maggiore di zero")
        BigDecimal fatturatoAnnuale,

        @NotNull(message = "La data di inserimento è obbligatoria")
        @PastOrPresent(message = "La data di inserimento non può essere futura")
        LocalDate dataInserimento,

        @NotNull(message = "La data dell'ultimo contatto è obbligatoria")
        @PastOrPresent(message = "La data dell'ultimo contatto non può essere futura")
        LocalDate dataUltimoContatto,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @Pattern(regexp = "^[0-9]{11}$", message = "La Partita IVA deve avere 11 numeri")
        String partitaIva
) {}