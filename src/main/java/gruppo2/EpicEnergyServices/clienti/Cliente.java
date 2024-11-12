package gruppo2.EpicEnergyServices.clienti;

import gruppo2.EpicEnergyServices.indirizzo.Indirizzo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "clienti")
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private BigDecimal fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sede_legale_id", referencedColumnName = "id")
    private Indirizzo sedeLegale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sede_operativa_id", referencedColumnName = "id")
    private Indirizzo sedeOperativa;


    public Cliente(String ragioneSociale, Indirizzo sedeOperativa,
                   Indirizzo sedeLegale, TipoCliente tipoCliente,
                   String logoAziendale, String telefonoContatto,
                   String cognomeContatto, String nomeContatto,
                   String telefono, String pec, String emailContatto,
                   BigDecimal fatturatoAnnuale, LocalDate dataInserimento,
                   LocalDate dataUltimoContatto, String email, String partitaIva) {

        this.ragioneSociale = ragioneSociale;
        this.sedeOperativa = sedeOperativa;
        this.sedeLegale = sedeLegale;
        this.tipoCliente = tipoCliente;
        this.logoAziendale = logoAziendale;
        this.telefonoContatto = telefonoContatto;
        this.cognomeContatto = cognomeContatto;
        this.nomeContatto = nomeContatto;
        this.telefono = telefono;
        this.pec = pec;
        this.emailContatto = emailContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.email = email;
        this.partitaIva = partitaIva;
    }
}
