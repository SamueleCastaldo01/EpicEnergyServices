package gruppo2.EpicEnergyServices.entities;

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

}
