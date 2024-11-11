package gruppo2.EpicEnergyServices.provincia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue
    private long id;
    private String provincia;
    private String regione;
    private String sigla;

    public Provincia() {}

    public Provincia(String provincia, String regione, String sigla) {
        this.provincia = provincia;
        this.regione = regione;
        this.sigla = sigla;
    }

    public long getId() {
        return id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "id=" + id +
                ", provincia='" + provincia + '\'' +
                ", regione='" + regione + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}
