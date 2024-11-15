package gruppo2.EpicEnergyServices.fatture;

import gruppo2.EpicEnergyServices.clienti.Cliente;
import gruppo2.EpicEnergyServices.entities.Utente;
import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    FatturaService fatturaService;

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        try {
            return this.fatturaService.findAll(page, size, sortBy);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero delle fatture: " + e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura save(@RequestBody @Validated FatturaDTO body, BindingResult validationResult, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        try {
            return this.fatturaService.save(body, currentAuthenticatedUser);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio della fattura: " + e.getMessage());
        }
    }

    //l' eliminazione deve avvenire solamente per un organizzatore
    //con lo stesso id utente di chi lo ha creato
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        try {
            this.fatturaService.findByIdAndDelete(id, currentAuthenticatedUser);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Fattura con id " + id + " non trovata.");
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione della fattura: " + e.getMessage());
        }
    }

    //query richieste dalla traccia--------------------------------
    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByClienteId(@PathVariable Long clienteId) {
        try {
            return fatturaService.findByClienteId(clienteId);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per cliente: " + e.getMessage());
        }
    }

    @GetMapping("/cliente/nomecontatto/{nomeContatto}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByClienteNome(@PathVariable String nomeContatto) {
        try {
            return fatturaService.findByClienteNome(nomeContatto);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per cliente: " + e.getMessage());
        }
    }

    @GetMapping("/stato-fattura/{statoFatturaId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByStatoFatturaId(@PathVariable Long statoFatturaId) {
        try {
            return fatturaService.findByStatoId(statoFatturaId);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per stato: " + e.getMessage());
        }
    }

    @GetMapping("/stato-fattura/nomestato/{stato}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByStatoNome(@PathVariable String stato) {
        try {
            return fatturaService.findByStatoNome(stato);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per stato: " + e.getMessage());
        }
    }

    @GetMapping("/range")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByDataRange(@RequestParam LocalDate startDate,
                                         @RequestParam LocalDate endDate) {
        try {
            return fatturaService.findByDataRange(startDate, endDate);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per intervallo di date: " + e.getMessage());
        }
    }

    @GetMapping("/anno")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByAnno(@RequestParam int anno) {
        try {
            return fatturaService.findByAnno(anno);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per anno: " + e.getMessage());
        }
    }

    @GetMapping("/importo-range")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByImportoRange(@RequestParam double importoMin,
                                            @RequestParam double importoMax) {
        try {
            return fatturaService.findByImportoRange(importoMin, importoMax);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per intervallo di importo: " + e.getMessage());
        }
    }
}


    /*
    @GetMapping("/fatture")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findFatture(
            @RequestParam(required = false) Long clienteId,
            @RequestParam(required = false) Long statoFatturaId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) Integer anno,
            @RequestParam(required = false) Double importoMin,
            @RequestParam(required = false) Double importoMax,
            @RequestParam(defaultValue = "0") int page) {

        return fatturaService.findFatture(clienteId, statoFatturaId, startDate, endDate, anno, importoMin, importoMax, page);
    }

     */
















