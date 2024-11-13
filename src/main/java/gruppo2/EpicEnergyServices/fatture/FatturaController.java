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

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findALl(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy   ) {
        return this.fatturaService.findAll(page, size, sortBy);
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

        return this.fatturaService.save(body, currentAuthenticatedUser);
    }

    //l' eliminazione deve avvenire solamente per un organizzatore
    //con lo stesso id utente di chi lo ha creato
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        this.fatturaService.findByIdAndDelete(id, currentAuthenticatedUser);
    }

    //query richieste dalla traccia--------------------------------
    @GetMapping("/cliente/{clienteId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByClienteId(@PathVariable Long clienteId) {
        return fatturaService.findByClienteId(clienteId);
    }

    @GetMapping("/stato-fattura/{statoFatturaId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByStatoFatturaId(@PathVariable Long statoFatturaId) {
        return fatturaService.findByStatoId(statoFatturaId);
    }

    @GetMapping("/{clienteId}/range")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByDataRange(@PathVariable Long clienteId,
                                         @RequestParam LocalDate startDate,
                                         @RequestParam LocalDate endDate) {
        return fatturaService.findByDataRange(startDate, endDate);
    }

    @GetMapping("/anno")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByAnno(@RequestParam int anno) {
        return fatturaService.findByAnno(anno);
    }

    @GetMapping("/importo-range")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Fattura> findByImportoRange(@RequestParam double importoMin,
                                            @RequestParam double importoMax) {
        return fatturaService.findByImportoRange(importoMin, importoMax);
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

}














