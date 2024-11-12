package gruppo2.EpicEnergyServices.fatture;

import gruppo2.EpicEnergyServices.clienti.Cliente;
import gruppo2.EpicEnergyServices.entities.Utente;
import gruppo2.EpicEnergyServices.fatture.statoFatture.StatoFattura;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "fatture")
public class Fattura {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    private LocalDate data;
    private double importo;
    private int numero;
    @ManyToOne
    @JoinColumn(name = "id_stato_fatture")
    private StatoFattura statoFattura;
    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Fattura() {}

    public Fattura(Cliente cliente, LocalDate data, double importo, int numero, StatoFattura statoFattura, Utente utente) {
        this.cliente = cliente;
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.statoFattura = statoFattura;
        this.utente = utente;
    }

    public long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public StatoFattura getStatoFattura() {
        return statoFattura;
    }

    public void setStatoFattura(StatoFattura statoFattura) {
        this.statoFattura = statoFattura;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Fattura{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", data=" + data +
                ", importo=" + importo +
                ", numero=" + numero +
                ", statoFattura=" + statoFattura +
                ", utente=" + utente +
                '}';
    }
}
