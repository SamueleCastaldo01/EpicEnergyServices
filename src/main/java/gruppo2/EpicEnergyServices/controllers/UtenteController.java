package gruppo2.EpicEnergyServices.controllers;


import gruppo2.EpicEnergyServices.entities.Utente;
import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.payloads.NewUtenteDTO;
import gruppo2.EpicEnergyServices.services.FileUploadService;
import gruppo2.EpicEnergyServices.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @Autowired
    FileUploadService fileUploadService;

    //questo dovrebbe essere visto solamente dall'admin, ma non lo ho aggiunto perché non è richiesto
    @GetMapping
    public List<Utente> findAll() {
        return this.utenteService.findAll();
    }

    // /Me endpoints----------------------------------------------------------------
    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public Utente updateProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser, @RequestBody @Validated NewUtenteDTO body) {
        return this.utenteService.findByIdAndUpdate(currentAuthenticatedUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Utente currentAuthenticatedUser) {
        this.utenteService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }

    @PostMapping("/me/avatar")
    public Utente uploadAvatar(@AuthenticationPrincipal Utente currentAuthenticatedUser,
                               @RequestParam("file") MultipartFile file) {
        try {
            // Carica l'immagine su Cloudinary e ottieni l'URL dell'immagine
            String avatarUrl = fileUploadService.uploadFile(file, currentAuthenticatedUser.getId());

            // Imposta l'URL dell'avatar nell'entità utente e salva l'utente aggiornato
            currentAuthenticatedUser.setAvatar(avatarUrl);
            return utenteService.save(currentAuthenticatedUser);
        } catch (IOException e) {
            // Usa ResponseStatusException per gestire l'errore con codice di stato HTTP
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Errore durante l'upload dell'avatar: " + e.getMessage(), e);
        }
    }


    // il resto dei metodi----------------------------------------------------------------
    //questi sono tutti metodi da parte del admin
    //questi in realtà non servono, ma li ho messi per testare
    @GetMapping("/{id}")
    public Utente findById(@PathVariable long id) {
        return this.utenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utente save(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.utenteService.save(body);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        // Ovunque ci sia un body bisognerebbe validarlo!
        return this.utenteService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.utenteService.findByIdAndDelete(id);
    }
}
