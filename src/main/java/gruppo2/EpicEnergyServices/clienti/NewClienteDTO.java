package gruppo2.EpicEnergyServices.clienti;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewClienteDTO(

        @NotBlank(message = "Ragione Sociale è obbligatoria")
        @Size(max = 100, message = "La Ragione Sociale non può superare i 100 caratteri")
        String ragioneSociale,

        @Positive(message = "L'id della sede operativa deve essere positivo")
        long idSedeOperativa,

        @Positive(message = "L'id della sede legale deve essere positivo")
        long idSedeLegale,

        @NotNull(message = "Tipo cliente è obbligatorio")
        TipoCliente tipoCliente,

        String logoAziendale,

        @NotBlank(message = "Il numero di telefono del contatto è obbligatorio")
        @Pattern(regexp = "\\d{1,10}", message = "Il telefono deve essere un numero di 10 cifre")
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

        @NotBlank(message = "L'email di contatto è obbligatoria")
        @Email(message = "Email di contatto non valida")
        String emailContatto,

        @NotNull(message = "Il fatturato annuale è obbligatorio")
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

        @NotBlank(message = "La Partita IVA è obbligatoria")
        @Pattern(regexp = "^[0-9]{11}$", message = "La Partita IVA deve avere 11 numeri")
        String partitaIva
) {}