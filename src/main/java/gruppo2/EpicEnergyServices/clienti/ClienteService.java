package gruppo2.EpicEnergyServices.clienti;


import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.exceptions.NotFoundException;
import gruppo2.EpicEnergyServices.indirizzo.IndirizzoRepository;
import gruppo2.EpicEnergyServices.indirizzo.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IndirizzoService indirizzoService;

    public Page<Cliente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.clienteRepository.findAll(pageable);
    }

    public Page<Cliente> getAllClientsSorted(String sortBy, Pageable pageable) {
        switch (sortBy.toLowerCase()) {
            case "nomecontatto":
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nomeContatto").ascending());
                break;
            case "fatturatoannuale":
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("fatturatoAnnuale").ascending());
                break;
            case "datainserimento":
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("dataInserimento").descending());
                break;
            case "dataultimocontatto":
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("dataUltimoContatto").descending());
                break;
            default:
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nomeContatto").ascending());
                break;
        }
        return clienteRepository.findAll(pageable);
    }

    public Cliente findClienteById(long clienteId) {
        return this.clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException(clienteId));
    }

    public Cliente save(NewClienteDTO body) {
        this.clienteRepository.findByEmail(body.email()).ifPresent(
                cliente -> {
                    throw new BadRequestException("Email" + body.email() + "giá in uso! inseriscine una nuova.");
                }
        );
        Cliente newCLiente = new Cliente(
                body.ragioneSociale(),
                this.indirizzoService.findById(body.idSedeOperativa()),
                this.indirizzoService.findById(body.idSedeLegale()),
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

    public Cliente findByIdAndUpdate(long clienteId, NewClienteDTO body) {

        Cliente found = this.findClienteById(clienteId);
        if(found.getEmail().equals(body.email())) {
            this.clienteRepository.findByEmail(body.email()).ifPresent(
                    cliente -> {
                        throw new BadRequestException("Email" + body.email() + "giá in uso!");
                    }
            );
        }
        found.setRagioneSociale(body.ragioneSociale());
        found.setSedeOperativa(this.indirizzoService.findById(body.idSedeLegale()));
        found.setSedeLegale(this.indirizzoService.findById(body.idSedeOperativa()));
        found.setTipoCliente(body.tipoCliente());
        found.setLogoAziendale(body.logoAziendale());
        found.setTelefonoContatto(body.telefonoContatto());
        found.setCognomeContatto(body.cognomeContatto());
        found.setNomeContatto(body.nomeContatto());
        found.setTelefono(body.telefono());
        found.setPec(body.pec());
        found.setEmailContatto(body.emailContatto());
        found.setFatturatoAnnuale(body.fatturatoAnnuale());
        found.setDataInserimento(body.dataInserimento());
        found.setDataUltimoContatto(body.dataUltimoContatto());
        found.setEmail(body.email());
        found.setPartitaIva(body.partitaIva());

        return this.clienteRepository.save(found);
    }

    public void findByIdAndDelete (long clienteId) {
            Cliente found = this.findClienteById(clienteId);
            this.clienteRepository.delete(found);
    }

    public Page<Cliente> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return clienteRepository.findAll(pageable);
    }


    //vari ordinamenti: ordinamento---------------------
    public Page<Cliente> findAllSortedByNomeContatto(Pageable pageable) {
        return clienteRepository.findAllByOrderByNomeContattoAsc(pageable);
    }

    public Page<Cliente> findAllSortedByFatturatoAnnuale(Pageable pageable) {
        return clienteRepository.findAllByOrderByFatturatoAnnualeAsc(pageable);
    }

    public Page<Cliente> findAllSortedByDataInserimento(Pageable pageable) {
        return clienteRepository.findAllByOrderByDataInserimentoDesc(pageable);
    }

    public Page<Cliente> findAllSortedByDataUltimoContatto(Pageable pageable) {
        return clienteRepository.findAllByOrderByDataUltimoContattoDesc(pageable);
    }

    //filtro fatturato -----------------------------
    public Page<Cliente> findByFatturatoAnnualeBetween(BigDecimal minFatturato, BigDecimal maxFatturato, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return clienteRepository.findByFatturatoAnnualeBetween(minFatturato, maxFatturato, pageable);
    }

    //filtro data inserimento
    public Page<Cliente> findByDataInserimento(LocalDate dataInserimento, int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 10);
        List<Cliente> clientiFiltrati = clienteRepository.findByDataInserimento(dataInserimento);
        return new PageImpl<>(clientiFiltrati, pageable, clientiFiltrati.size());
    }

    //filtro data ultimo contatto
    public Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 10);
        List<Cliente> clientiFiltrati = clienteRepository.findByDataUltimoContatto(dataUltimoContatto);
        return new PageImpl<>(clientiFiltrati, pageable, clientiFiltrati.size());
    }


    //filtro nome Contatto
    public Page<Cliente> findByNomeContatto(String nomeContatto, int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 10);
        List<Cliente> clientiFiltrati = clienteRepository.findByNomeContattoStartingWithIgnoreCase(nomeContatto);
        return new PageImpl<>(clientiFiltrati, pageable, clientiFiltrati.size());
    }


    //find
    public Page<Cliente> findFilteredClients(
            BigDecimal minFatturato,
            BigDecimal maxFatturato,
            LocalDate dataInserimento,
            LocalDate dataUltimoContatto,
            String nomeContatto,
            int page) {

        Pageable pageable = PageRequest.of(Math.max(page, 0), 10);

        if (minFatturato != null && maxFatturato != null) {
            return clienteRepository.findByFatturatoAnnualeBetween(minFatturato, maxFatturato, pageable);
        }

        if (dataInserimento != null) {
            List<Cliente> clientiFiltrati = clienteRepository.findByDataInserimento(dataInserimento);
            return new PageImpl<>(clientiFiltrati, pageable, clientiFiltrati.size());
        }

        if (dataUltimoContatto != null) {
            List<Cliente> clientiFiltrati = clienteRepository.findByDataUltimoContatto(dataUltimoContatto);
            return new PageImpl<>(clientiFiltrati, pageable, clientiFiltrati.size());
        }

        if (nomeContatto != null && !nomeContatto.isEmpty()) {
            List<Cliente> clientiFiltrati = clienteRepository.findByNomeContattoStartingWithIgnoreCase(nomeContatto);
            return new PageImpl<>(clientiFiltrati, pageable, clientiFiltrati.size());
        }

        return clienteRepository.findAllByOrderByNomeContattoAsc(pageable);
    }


}
