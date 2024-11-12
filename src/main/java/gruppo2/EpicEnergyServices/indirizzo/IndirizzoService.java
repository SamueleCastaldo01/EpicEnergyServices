package gruppo2.EpicEnergyServices.indirizzo;

import gruppo2.EpicEnergyServices.comune.Comune;
import gruppo2.EpicEnergyServices.comune.ComuneRepository;
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
        Comune comune = comuneRepository.findByComune(body.comune());
        Indirizzo newIndirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), body.cap(), comune);
        return this.indirizzoRepository.save(newIndirizzo);
    }

    public Page<Indirizzo> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.indirizzoRepository.findAll(pageable);
    }

    public Indirizzo findById(long id) {
        return this.indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Indirizzo findByIdAndUpdate(long id, NewIndirizzoDTO body) {
        Comune comune = comuneRepository.findByComune(body.comune());
        Indirizzo found = this.findById(id);
        found.setVia(body.via());
        found.setCivico(body.civico());
        found.setLocalita(body.localita());
        found.setCap(body.cap());
        found.setComune(comune);

        return this.indirizzoRepository.save(found);
    }

    public void findByIdAndDelete(long id) {
        Indirizzo found = this.findById(id);
        this.indirizzoRepository.delete(found);
    }


}
