package gruppo2.EpicEnergyServices.indirizzo;

import gruppo2.EpicEnergyServices.comune.Comune;
import gruppo2.EpicEnergyServices.comune.ComuneRepository;
import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service

public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    public Indirizzo save(NewIndirizzoDTO body) {
        try {
            Comune comune = comuneRepository.findByComune(body.comune());
            if (comune == null) {
                throw new BadRequestException("Comune non trovato!");
            }
            long cap = Long.parseLong(body.cap());

            Indirizzo newIndirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), cap, comune);
            return this.indirizzoRepository.save(newIndirizzo);
        } catch (BadRequestException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new BadRequestException("Il CAP deve essere un numero valido");
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il salvataggio dell'indirizzo: " + e.getMessage());
        }
    }

    public Page<Indirizzo> findAll(int page, int size, String sortBy) {
        try {
            if (size > 100) size = 100;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            return this.indirizzoRepository.findAll(pageable);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante il recupero degli indirizzi: " + e.getMessage());
        }
    }

    public Indirizzo findById(long id) {
        try {
            return this.indirizzoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Indirizzo non trovato con id: " + id));
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BadRequestException("Errore durante la ricerca dell'indirizzo: " + e.getMessage());
        }
    }

    public Indirizzo findByIdAndUpdate(long id, NewIndirizzoDTO body) {
        try {
            Comune comune = comuneRepository.findByComune(body.comune());
            if (comune == null) {
                throw new BadRequestException("Comune non trovato!");
            }
            long cap = Long.parseLong(body.cap());

            Indirizzo found = this.findById(id);
            found.setVia(body.via());
            found.setCivico(body.civico());
            found.setLocalita(body.localita());
            found.setCap(cap);  // Set con long
            found.setComune(comune);

            return this.indirizzoRepository.save(found);
        } catch (BadRequestException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw new BadRequestException("Il CAP deve essere un numero valido");
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'aggiornamento dell'indirizzo: " + e.getMessage());
        }
    }

    public void findByIdAndDelete(long id) {
        try {
            Indirizzo found = this.findById(id);
            this.indirizzoRepository.delete(found);
        } catch (Exception e) {
            throw new BadRequestException("Errore durante l'eliminazione dell'indirizzo: " + e.getMessage());
        }
    }
}
