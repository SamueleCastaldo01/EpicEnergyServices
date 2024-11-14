package gruppo2.EpicEnergyServices.fatture;

import gruppo2.EpicEnergyServices.clienti.Cliente;
import gruppo2.EpicEnergyServices.clienti.ClienteService;
import gruppo2.EpicEnergyServices.entities.Utente;
import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.exceptions.NotFoundException;
import gruppo2.EpicEnergyServices.fatture.statoFatture.StatoFattura;
import gruppo2.EpicEnergyServices.fatture.statoFatture.StatoFatturaService;
import gruppo2.EpicEnergyServices.indirizzo.Indirizzo;
import gruppo2.EpicEnergyServices.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FatturaService {

    @Autowired
    FatturaRepository fatturaRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    StatoFatturaService statoFatturaService;

    @Autowired
    UtenteService utenteService;

    //get all
    public Page<Fattura> findAll(int page, int size, String sortBy) {
        try {
            if (size > 100) size = 100;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return this.fatturaRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero delle fatture: " + e.getMessage());
        }
    }

    public Fattura findById(long id) {
        try {
            return this.fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException("Fattura con id " + id + " non trovata"));
        } catch (NotFoundException e) {
            throw e; // Rilancia l'eccezione NotFoundException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero della fattura: " + e.getMessage());
        }
    }

    //get by id Cliente
    public Page<Fattura> findByClienteId(Long clienteId) {
        try {
            int page = 0;
            int size = 10;
            Pageable pageable = PageRequest.of(page, size);
            return fatturaRepository.findByClienteId(clienteId, pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per cliente: " + e.getMessage());
        }
    }

    //get by id StatoFattura
    public Page<Fattura> findByStatoId(Long statoId) {
        try {
            int page = 0;
            int size = 10;
            Pageable pageable = PageRequest.of(page, size);
            return fatturaRepository.findByStatoFatturaId(statoId, pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per stato: " + e.getMessage());
        }
    }

    //POST --------------------------------------------
    public Fattura save(FatturaDTO body, Utente utente) {
        try {
            Cliente cliente = clienteService.findClienteById(body.id_cliente());
            StatoFattura statoFattura = statoFatturaService.findById(body.id_stato_fattura());


            Fattura newFattura = new Fattura(cliente, body.data(), body.importo(), body.numero(), statoFattura, utente);
            return this.fatturaRepository.save(newFattura);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio della fattura: " + e.getMessage());
        }
    }


    //DELETE --------------------------------------------
    //l'eliminazione la può fare solamente l'utente che lo ha creato
    public void findByIdAndDelete(long id, Utente utente) {
        try {
            Fattura found = this.findById(id);
            if (found.getUtente().getId() != utente.getId()) {
                throw new BadRequestException("Non hai i permessi per eliminare questa fattura");
            }
            this.fatturaRepository.delete(found);
        } catch (BadRequestException e) {
            throw e; // Rilancia l'eccezione BadRequestException
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione della fattura: " + e.getMessage());
        }
    }

    //filter range data
    public Page<Fattura> findByDataRange(LocalDate startDate, LocalDate endDate) {
        try {
            int page = 0;
            int size = 10;
            Pageable pageable = PageRequest.of(page, size);
            return fatturaRepository.findByDataBetween(startDate, endDate, pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per intervallo di date: " + e.getMessage());
        }
    }

    //filter fattura in quell'anno
    public Page<Fattura> findByAnno(int anno) {
        try {
            int page = 0;
            int size = 10;
            Pageable pageable = PageRequest.of(page, size);
            return fatturaRepository.findByAnno(anno, pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per anno: " + e.getMessage());
        }
    }

    //filter fattura range import
    public Page<Fattura> findByImportoRange(double importoMin, double importoMax) {
        try {
            int page = 0;
            int size = 10;
            Pageable pageable = PageRequest.of(page, size);
            return fatturaRepository.findByImportoBetween(importoMin, importoMax, pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca delle fatture per intervallo di importo: " + e.getMessage());
        }
    }
}
