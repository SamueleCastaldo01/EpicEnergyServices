package gruppo2.EpicEnergyServices.indirizzo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewIndirizzoDTO(
        @NotEmpty(message = "La via è obbligatoria")
        String via,
        @NotEmpty(message = "Il numero civico é obbligatorio")
        Long civico,
        @NotEmpty(message = "La localitá è obbligatoria")
        String localita,
        @NotEmpty(message = "Il cap è obbligatorio")
        Integer cap,
        @NotNull
        String comune
) {
}
