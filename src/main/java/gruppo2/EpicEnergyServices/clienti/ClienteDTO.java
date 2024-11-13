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

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Email non valida")
        String email,

        @NotNull(message = "Data di Inserimento è obbligatoria")
        @PastOrPresent(message = "La Data di Inserimento non può essere futura")
        LocalDate dataInserimento,

        @NotNull(message = "Data dell'ultimo contatto è obbligatoria")
        @PastOrPresent(message = "La Data dell'ultimo contatto non può essere futura")
        LocalDate dataUltimoContatto,

        @NotNull(message = "Il fatturato annuale è obbligatorio")
        @Positive(message = "Il fatturato annuale deve essere maggiore di zero")
        BigDecimal fatturatoAnnuale,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "PEC non valida")
        String pec,

        @NotBlank(message = "Il telefono è obbligatorio")
        @Pattern(regexp = "\\+?\\d{1,4}\\s?\\d{10}", message = "Il telefono deve essere un numero di 10 cifre")
        String telefono,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Email di contatto non valida")
        String emailContatto,

        @NotBlank(message = "Nome del contatto è obbligatorio")
        String nomeContatto,

        @NotBlank(message = "Cognome del contatto è obbligatorio")
        String cognomeContatto,

        @NotBlank(message = "Il telefono è obbligatorio")
        @Pattern(regexp = "\\+?\\d{1,4}\\s?\\d{10}", message = "Il telefono deve essere un numero di 10 cifre")
        String telefonoContatto,

        String logoAziendale,

        @NotNull(message = "Tipo cliente è obbligatorio")
        TipoCliente tipoCliente
) {}
