package gruppo2.EpicEnergyServices.services;


import gruppo2.EpicEnergyServices.entities.Utente;
import gruppo2.EpicEnergyServices.exceptions.BadRequestException;
import gruppo2.EpicEnergyServices.exceptions.NotFoundException;
import gruppo2.EpicEnergyServices.payloads.NewUtenteDTO;
import gruppo2.EpicEnergyServices.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente findById(long id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato"));
    }

    //GET -------------------------------------
    public List<Utente> findAll() {
        return this.utenteRepository.findAll();
    }

    //POST -------------------------------------
    public Utente save(NewUtenteDTO body) {
        //controllo se ci sta già un altro con la stessa email
        this.utenteRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.email() + " già in uso!");
                }
        );
        //controllo anche dell'username, deve essere univoco
        this.utenteRepository.findByUsername(body.username()).ifPresent(
                user -> {
                    throw new BadRequestException("Username " + body.username() + " è già in uso!");
                }
        );
        Utente newUtente = new Utente(body.username(), body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()));
        return this.utenteRepository.save(newUtente);
    }

    //PUT --------------------------------------------
    public Utente findByIdAndUpdate(long id, NewUtenteDTO body) {
        Utente found = this.findById(id);

        if (!found.getEmail().equals(body.email())) {
            this.utenteRepository.findByEmail(body.email()).ifPresent(
                    user -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso!");
                    }
            );
        }
        found.setUsername(body.username());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());

        return this.utenteRepository.save(found);
    }

    //DELETE --------------------------------------------
    public void findByIdAndDelete(long id) {
        Utente found = this.findById(id);
        this.utenteRepository.delete(found);
    }
}
