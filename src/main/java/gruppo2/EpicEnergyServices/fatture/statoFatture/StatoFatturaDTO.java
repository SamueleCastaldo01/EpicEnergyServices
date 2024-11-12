package gruppo2.EpicEnergyServices.fatture.statoFatture;

import jakarta.validation.constraints.NotEmpty;

public record StatoFatturaDTO(
        @NotEmpty(message = "Lo stato è obbligatorio")
        String stato
) {
}
