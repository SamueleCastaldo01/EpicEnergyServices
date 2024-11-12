package gruppo2.EpicEnergyServices.clienti;



import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    public Page<Cliente> findALl(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy   ) {
        return this.clienteService.findAll(page, size, sortBy);
    }

    @PostMapping
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
    public Cliente findById(@PathVariable long clienteId) {
        return this.clienteService.findClienteById(clienteId);
    }

    @PutMapping("/{clienteId}")
    public Cliente findByIdAndUpdate(@PathVariable long clienteId, @RequestBody @Validated NewClienteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        return this.clienteService.findByIdAndUpdate(clienteId, body);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long clienteId) {
        this.clienteService.findByIdAndDelete(clienteId);
    }



    //ordinamento-------------------------------------
    @GetMapping("/sorted-by-nome")
    public Page<Cliente> getAllClientsSortedByNomeContatto(Pageable pageable) {
        return clienteService.findAllSortedByNomeContatto(pageable);
    }

    @GetMapping("/sorted-by-fatturato")
    public Page<Cliente> getAllClientsSortedByFatturatoAnnuale(Pageable pageable) {
        return clienteService.findAllSortedByFatturatoAnnuale(pageable);
    }

    @GetMapping("/sorted-by-data-inserimento")
    public Page<Cliente> getAllClientsSortedByDataInserimento(Pageable pageable) {
        return clienteService.findAllSortedByFatturatoAnnuale(pageable);
    }

    @GetMapping("/sorted-by-data-ultimo-contatto")
    public Page<Cliente> getAllClientsSortedByDataContatto(Pageable pageable) {
        return clienteService.findAllSortedByFatturatoAnnuale(pageable);
    }

    //vari filtri ------------------------------------------------------
    // fatturato
    @GetMapping("/filtered-by-fatturato")
    public Page<Cliente> getClientsByFatturatoAnnuale(
            @RequestParam BigDecimal minFatturato,
            @RequestParam BigDecimal maxFatturato) {
        return clienteService.findByFatturatoAnnualeBetween(minFatturato, maxFatturato);
    }

    @GetMapping("/filtered-by-data-inserimento")
    public Page<Cliente> getClientsByDataInserimento(
            @RequestParam LocalDate dataInserimento,
            @RequestParam(defaultValue = "0") int page) {
        return clienteService.findByDataInserimento(dataInserimento, page);
    }
}
