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
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.statoFatturaRepository.findAll(pageable);
    }

    //GET --------------------------------------------
    public List<StatoFattura> findAll() {
        return this.statoFatturaRepository.findAll();
    }

    public StatoFattura findById(long id) {
        return this.statoFatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //POST --------------------------------------------
    public StatoFattura save(StatoFatturaDTO body) {
        this.statoFatturaRepository.findByStato(body.stato()).ifPresent(
                fattura -> {
                    throw new BadRequestException("Stato " + body.stato() + " già in uso!");
                }
        );
        // Crea e salva il nuovo StatoFattura
        StatoFattura newStatoFattura = new StatoFattura(body.stato());
        return this.statoFatturaRepository.save(newStatoFattura);
    }


    //PUT --------------------------------------------
    public StatoFattura findByIdAndUpdate(long id, StatoFatturaDTO body) {
        StatoFattura found = this.findById(id);
        found.setNomeStato(body.stato());

        return this.statoFatturaRepository.save(found);
    }

    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id) {
        StatoFattura found = this.findById(id);

        this.statoFatturaRepository.delete(found);
    }

}
