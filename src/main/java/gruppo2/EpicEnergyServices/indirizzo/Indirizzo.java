package gruppo2.EpicEnergyServices.indirizzo;

import gruppo2.EpicEnergyServices.comune.Comune;
import jakarta.persistence.*;

@Entity

@Table(name = "indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue
    private long id;
    private String via;
    private long civico;
    private String localita;
    private long cap;

    @ManyToOne
    @JoinColumn(name = "id_comune")
    private Comune comune;

    public Indirizzo() {
    }

    public Indirizzo(String via, long civico, String localita, long cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
    }

    public Comune getComune() {
        return comune;
    }

    public void setComune(Comune comune) {
        this.comune = comune;
    }

    public long getCap() {
        return cap;
    }

    public void setCap(long cap) {
        this.cap = cap;
    }

    public String getLocalita() {
        return localita;
    }

    public void setLocalita(String localita) {
        this.localita = localita;
    }

    public long getCivico() {
        return civico;
    }

    public void setCivico(long civico) {
        this.civico = civico;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "id=" + id +
                ", via='" + via + '\'' +
                ", civico=" + civico +
                ", localita='" + localita + '\'' +
                ", cap=" + cap +
                ", comune=" + comune +
                '}';
    }
}
