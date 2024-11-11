package gruppo2.EpicEnergyServices.controllers;


import gruppo2.EpicEnergyServices.entities.Utente;
import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.payloads.NewUtenteDTO;
import gruppo2.EpicEnergyServices.payloads.UserLoginDTO;
import gruppo2.EpicEnergyServices.payloads.UserLoginResponseDTO;
import gruppo2.EpicEnergyServices.services.AuthService;
import gruppo2.EpicEnergyServices.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService dipendenteService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente save(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.dipendenteService.save(body);
    }
}
