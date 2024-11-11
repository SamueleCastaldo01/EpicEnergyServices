package gruppo2.EpicEnergyServices.payloads;

import gruppo2.EpicEnergyServices.comune.Comune;
import jakarta.validation.constraints.NotEmpty;

public record NewIndirizzoDTO(
        @NotEmpty(message = "La via è obbligatoria")
        String via,
        @NotEmpty(message = "Il numero civico é obbligatorio")
        Long civico,
        @NotEmpty(message = "La localitá è obbligatoria")
        String localita,
        @NotEmpty(message = "Il cap è obbligatorio")
        Integer cap,
        @NotEmpty(message = "Il comune è obbligatorio")
        Comune comune
) {
}
