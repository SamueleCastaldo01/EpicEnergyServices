package gruppo2.EpicEnergyServices.fatture.statoFatture;

import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stato-fatture")
public class StatoFatturaController {

    @Autowired
    StatoFatturaService statoFatturaService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<StatoFattura> findAll() {
        try {
            return this.statoFatturaService.findAll();
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli stati fattura: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public StatoFattura findById(@PathVariable long id) {
        try {
            return this.statoFatturaService.findById(id);
        } catch (NotFoundException e) {
            throw e; 
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero dello StatoFattura con id " + id + ": " + e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public StatoFattura save(@RequestBody @Validated StatoFatturaDTO body, BindingResult validationResult) {
        try {
            if (validationResult.hasErrors()) {
                String message = validationResult.getAllErrors().stream()
                        .map(objectError -> objectError.getDefaultMessage())
                        .collect(Collectors.joining(". "));
                throw new BadRequestException("Ci sono stati errori nel payload! " + message);
            }
            return this.statoFatturaService.save(body);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio dello stato fattura: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StatoFattura findByIdAndUpdate(@PathVariable long id, @RequestBody @Validated StatoFatturaDTO body, BindingResult validationResult) {
        try {
            if (validationResult.hasErrors()) {
                validationResult.getAllErrors().forEach(System.out::println);
                throw new BadRequestException("Ci sono stati errori nel payload!");
            }
            return this.statoFatturaService.findByIdAndUpdate(id, body);
        } catch (NotFoundException e) {
            throw e;
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento dello stato fattura con id " + id + ": " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        try {
            this.statoFatturaService.findByIdAndDelete(id);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione dello stato fattura con id " + id + ": " + e.getMessage());
        }
    }
}