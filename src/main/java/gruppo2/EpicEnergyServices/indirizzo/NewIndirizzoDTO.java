package gruppo2.EpicEnergyServices.indirizzo;

import jakarta.validation.constraints.*;

public record NewIndirizzoDTO(
        @NotEmpty(message = "La via è obbligatoria")
        @Size(max = 100, message = "La via non può essere più lunga di 100 caratteri")
        String via,

        @NotNull(message = "Il numero civico è obbligatorio")
        @Min(value = 1, message = "Il numero civico deve essere maggiore di 0")
        long civico,

        @NotEmpty(message = "La località è obbligatoria")
        @Size(max = 100, message = "La località non può essere più lunga di 100 caratteri")
        String localita,

        @NotNull(message = "Il CAP è obbligatorio")
        @Pattern(regexp = "^[0-9]{5}$", message = "Il CAP deve essere composto da 5 cifre")
        String cap,

        @NotNull(message = "Il comune è obbligatorio")
        String comune
) {
}