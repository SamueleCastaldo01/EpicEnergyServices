package gruppo2.EpicEnergyServices.clienti;



import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Cliente> findALl(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy   ) {
        return this.clienteService.findAll(page, size, sortBy);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Validated NewClienteDTO body, BindingResult validationResult) {
        if(validationResult.hasErrors()) {
            String message = validationResult
                    .getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(java.util.stream.Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.clienteService.save(body);
    }

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Cliente findById(@PathVariable long clienteId) {
        return this.clienteService.findClienteById(clienteId);
    }

    @PutMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente findByIdAndUpdate(@PathVariable long clienteId, @RequestBody @Validated NewClienteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        return this.clienteService.findByIdAndUpdate(clienteId, body);
    }

    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long clienteId) {
        this.clienteService.findByIdAndDelete(clienteId);
    }


    //ordinamento-------------------------------------
    @GetMapping("/sorted")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Cliente> getAllClientsSorted(@RequestParam String sortBy, Pageable pageable) {
        switch (sortBy) {
            case "nome":
                return clienteService.findAllSortedByNomeContatto(pageable);
            case "fatturato":
                return clienteService.findAllSortedByFatturatoAnnuale(pageable);
            case "data-inserimento":
                return clienteService.findAllSortedByDataInserimento(pageable);
            case "data-ultimo-contatto":
                return clienteService.findAllSortedByDataUltimoContatto(pageable);
            default:
                throw new IllegalArgumentException("Ordinamento non valido: " + sortBy);
        }
    }

    //vari filtri ------------------------------------------------------
    // fatturato
    /*
    @GetMapping("/filtered")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Cliente> getFilteredClients(
            @RequestParam(required = false) BigDecimal minFatturato,
            @RequestParam(required = false) BigDecimal maxFatturato,
            @RequestParam(required = false) LocalDate dataInserimento,
            @RequestParam(required = false) LocalDate dataUltimoContatto,
            @RequestParam(required = false) String nomeContatto,
            @RequestParam(defaultValue = "0") int page) {

        if (minFatturato != null && maxFatturato != null) {
            return clienteService.findByFatturatoAnnualeBetween(minFatturato, maxFatturato, page);
        }

        if (dataInserimento != null) {
            return clienteService.findByDataInserimento(dataInserimento, page);
        }

        if (dataUltimoContatto != null) {
            return clienteService.findByDataUltimoContatto(dataUltimoContatto, page);
        }

        if (nomeContatto != null && !nomeContatto.isEmpty()) {
            return clienteService.findByNomeContatto(nomeContatto, page);
        }

        return clienteService.findAll(page);
    } */

    @GetMapping("/filtered")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Page<Cliente> getFilteredClients(
            @RequestParam(required = false) BigDecimal minFatturato,
            @RequestParam(required = false) BigDecimal maxFatturato,
            @RequestParam(required = false) LocalDate dataInserimento,
            @RequestParam(required = false) LocalDate dataUltimoContatto,
            @RequestParam(required = false) String nomeContatto,
            @RequestParam(defaultValue = "0") int page) {

        return clienteService.findFilteredClients(
                minFatturato,
                maxFatturato,
                dataInserimento,
                dataUltimoContatto,
                nomeContatto,
                page
        );
    }


}
