package gruppo2.EpicEnergyServices.fatture;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record FatturaDTO(
        @NotNull(message = "L'id del cliente è obbligatorio")
        @Positive(message = "L'id del cliente deve essere un numero positivo")
        long id_cliente,

        @NotNull(message = "La data è obbligatoria")
        @PastOrPresent(message = "La data non può essere nel futuro")
        LocalDate data,

        @NotNull(message = "L'importo è obbligatorio")
        @Positive(message = "L'importo deve essere maggiore di zero")
        double importo,

        @NotNull(message = "Il numero è obbligatorio")
        @Positive(message = "Il numero della fattura deve essere un numero positivo")
        int numero,

        @NotNull(message = "L'id_stato_fattura è obbligatorio")
        @Positive(message = "L'id dello stato della fattura deve essere un numero positivo")
        long id_stato_fattura
) {
}