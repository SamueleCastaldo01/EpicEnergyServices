package gruppo2.EpicEnergyServices.indirizzo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewIndirizzoDTO(
        @NotEmpty(message = "La via è obbligatoria")
        String via,
        @NotNull(message = "Il numero civico é obbligatorio")
        long civico,
        @NotEmpty(message = "La localitá è obbligatoria")
        String localita,
        @NotNull(message = "Il numero civico é obbligatorio")
        long cap,
        @NotNull()
        String comune
) {
}
