package gruppo2.EpicEnergyServices.fatture.statoFatture;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "stato-fatture")
public class StatoFattura {
    @Id
    @GeneratedValue
    private long id;
    private String stato;

    public StatoFattura() {}

    public StatoFattura(String nomeStato) {
        this.stato = nomeStato;
    }

    public long getId() {
        return id;
    }

    public String getNomeStato() {
        return stato;
    }

    public void setNomeStato(String nomeStato) {
        this.stato = nomeStato;
    }

    @Override
    public String toString() {
        return "StatoFattura{" +
                "id=" + id +
                ", nomeStato='" + stato + '\'' +
                '}';
    }
}
