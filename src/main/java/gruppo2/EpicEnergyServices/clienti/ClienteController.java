package gruppo2.EpicEnergyServices.clienti;



import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/clienteId")
    public Cliente findById(@PathVariable long dipendenteid) {
        return this.clienteService.findClienteById(dipendenteid);
    }

    @PutMapping("/{cienteId}")
    public Cliente findByIdAndUpdate(@PathVariable long dipendenteid, @RequestBody @Validated NewClienteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }
        return this.clienteService.findByIdAndUpdate(dipendenteid, body);
    }

//    @DeleteMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void findByIdAndDelete(@PathVariable long dipendenteid) {
//        return this.clienteService.findIdAndDelete(dipendenteid);
//    }
//





}
