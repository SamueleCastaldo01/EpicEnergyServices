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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    FatturaService fatturaService;

    @GetMapping
    public Page<Fattura> findALl(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy   ) {
        return this.fatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Fattura> findByClienteId(@PathVariable Long clienteId) {
        return fatturaService.findByClienteId(clienteId);
    }

    @PostMapping
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id, @AuthenticationPrincipal Utente currentAuthenticatedUser) {
        this.fatturaService.findByIdAndDelete(id, currentAuthenticatedUser);
    }

}














