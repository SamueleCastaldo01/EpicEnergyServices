package gruppo2.EpicEnergyServices.fatture;

import gruppo2.EpicEnergyServices.clienti.Cliente;
import gruppo2.EpicEnergyServices.clienti.ClienteService;
import gruppo2.EpicEnergyServices.entities.Utente;
import gruppo2.EpicEnergyServices.exceptions.NotFoundException;
import gruppo2.EpicEnergyServices.fatture.statoFatture.StatoFattura;
import gruppo2.EpicEnergyServices.indirizzo.Indirizzo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FatturaService {

    @Autowired
    FatturaRepository fatturaRepository;

    @Autowired
    ClienteService clienteService;

    //get all
    public Page<Fattura> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.fatturaRepository.findAll(pageable);
    }

    public Fattura findById(long id) {
        return this.fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //POST --------------------------------------------
    /*
    public Fattura save(FatturaDTO body, Utente utente) {
        Fattura newEvento = new Fattura()
        return this.fatturaRepository.save(newEvento);
    }

     */

}
