package gruppo2.EpicEnergyServices.clienti;


import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.exceptions.NotFoundException;
import gruppo2.EpicEnergyServices.indirizzo.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public Page<Cliente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.clienteRepository.findAll(pageable);
    }

    public Cliente findClienteById(long clienteId) {
        return this.clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }

    public Cliente save(NewClienteDTO body) {
        this.clienteRepository.findbyEmailCliente(body.email()).ifPresent(
                cliente -> {
                    throw new BadRequestException("Email" + body.email() + "giá in uso! inseriscine una nuova.");
                }
        );
        Cliente newCLiente = new Cliente(
                body.ragioneSociale(),
                this.indirizzoRepository.findByIndirizzo(body.sedeOperativa()),
                this.indirizzoRepository.findByIndirizzo(body.sedeLegale()),
                body.tipoCliente(),
                body.logoAziendale(),
                body.telefonoContatto(),
                body.cognomeContatto(),
                body.nomeContatto(),
                body.telefono(),
                body.pec(),
                body.emailContatto(),
                body.fatturatoAnnuale(),
                body.dataInserimento(),
                body.dataUltimoContatto(),
                body.email(),
                body.partitaIva());

        return this.clienteRepository.save(newCLiente);
    }

    public Cliente findByIdAndUpdate(long dipendenteId, NewClienteDTO body) {

        Cliente found = this.findClienteById(dipendenteId);


        return this.clienteRepository.save(found);
    }






}
