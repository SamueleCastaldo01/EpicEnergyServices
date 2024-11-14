package gruppo2.EpicEnergyServices.fatture.statoFatture;

import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatoFatturaService {
    @Autowired
    StatoFatturaRepository statoFatturaRepository;

    //get all
    public Page<StatoFattura> findAll(int page, int size, String sortBy) {
        try {
            if (size > 100) size = 100;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return this.statoFatturaRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli stati fattura: " + e.getMessage());
        }
    }
    //GET --------------------------------------------
    public List<StatoFattura> findAll() {
        try {
            return this.statoFatturaRepository.findAll();
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli stati fattura: " + e.getMessage());
        }
    }

    public StatoFattura findById(long id) {
        try {
            return this.statoFatturaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("StatoFattura con id " + id + " non trovato"));
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero dello StatoFattura con id " + id + ": " + e.getMessage());
        }
    }
    //POST --------------------------------------------
    public StatoFattura save(StatoFatturaDTO body) {
        try {
            this.statoFatturaRepository.findByStato(body.stato()).ifPresent(
                    fattura -> {
                        throw new BadRequestException("Stato " + body.stato() + " già in uso!");
                    }
            );
            StatoFattura newStatoFattura = new StatoFattura(body.stato());
            return this.statoFatturaRepository.save(newStatoFattura);
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio dello stato fattura: " + e.getMessage());
        }
    }
    //PUT --------------------------------------------
    public StatoFattura findByIdAndUpdate(long id, StatoFatturaDTO body) {
        try {
            StatoFattura found = this.findById(id);
            found.setNomeStato(body.stato());
            return this.statoFatturaRepository.save(found);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento dello stato fattura con id " + id + ": " + e.getMessage());
        }
    }

    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id) {
        try {
            StatoFattura found = this.findById(id);
            this.statoFatturaRepository.delete(found);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione dello stato fattura con id " + id + ": " + e.getMessage());
        }
    }
}