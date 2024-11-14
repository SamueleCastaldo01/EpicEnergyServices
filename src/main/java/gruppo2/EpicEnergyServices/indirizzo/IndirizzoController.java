package gruppo2.EpicEnergyServices.indirizzo;


import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Indirizzo> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        try {
            return this.indirizzoService.findAll(page, size, sortBy);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli indirizzi: " + e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Indirizzo save(@RequestBody @Validated NewIndirizzoDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        try {
            return this.indirizzoService.save(body);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio dell'indirizzo: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Indirizzo findById(@PathVariable long id) {
        try {
            return this.indirizzoService.findById(id);
        } catch (Exception e) {
            throw new BadRequestException("Indirizzo con id " + id + " non trovato o errore durante il recupero.");
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Indirizzo findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated NewIndirizzoDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        try {
            return this.indirizzoService.findByIdAndUpdate(id, body);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento dell'indirizzo: " + e.getMessage());
        }
    }
}