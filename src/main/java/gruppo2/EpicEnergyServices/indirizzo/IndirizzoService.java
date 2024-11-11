package gruppo2.EpicEnergyServices.indirizzo;

import gruppo2.EpicEnergyServices.payloads.NewIndirizzoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public Indirizzo save(NewIndirizzoDTO body) {
        Indirizzo newIndirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), body.cap(), body.comune());
        return this.indirizzoRepository.save(newIndirizzo);
    }

    public List<Indirizzo> getAll() {
        return this.indirizzoRepository.findAll();
    }


}
