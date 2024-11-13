package gruppo2.EpicEnergyServices.fatture;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FatturaDTO(
    @NotNull(message = "L'id del cliente è obbligatorio")
    long id_cliente,
    @NotNull(message = "La data è obbligatoria")
    LocalDate data,
    @NotNull(message = "L'importo è obbligatorio")
    double importo,
    @NotNull(message = "Il numero è obbligatorio")
    int numero,
    @NotNull(message = "L'id_stato_fattura è obbligatorio")
    long id_stato_fattura
) {
}
