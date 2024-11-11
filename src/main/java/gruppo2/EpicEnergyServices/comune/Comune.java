package gruppo2.EpicEnergyServices.comune;

import gruppo2.EpicEnergyServices.provincia.Provincia;
import jakarta.persistence.*;

@Entity
@Table(name = "comuni")
public class Comune {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;
    private String comune;

    public Comune() {}

    public Comune(Provincia provincia, String comune) {
        this.provincia = provincia;
        this.comune = comune;
    }

    public long getId() {
        return id;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    @Override
    public String toString() {
        return "Comune{" +
                "id=" + id +
                ", provincia=" + provincia +
                ", comune='" + comune + '\'' +
                '}';
    }
}
