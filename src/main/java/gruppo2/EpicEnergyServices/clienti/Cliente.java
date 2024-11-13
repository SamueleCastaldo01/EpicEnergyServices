package gruppo2.EpicEnergyServices.clienti;

import gruppo2.EpicEnergyServices.indirizzo.Indirizzo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "clienti")
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sede_legale_id", referencedColumnName = "id")
    private Indirizzo sedeLegale;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sede_operativa_id", referencedColumnName = "id")
    private Indirizzo sedeOperativa;

    public Cliente() {}

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

    public long getId() {
        return id;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(LocalDate dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public LocalDate getDataUltimoContatto() {
        return dataUltimoContatto;
    }

    public void setDataUltimoContatto(LocalDate dataUltimoContatto) {
        this.dataUltimoContatto = dataUltimoContatto;
    }

    public BigDecimal getFatturatoAnnuale() {
        return fatturatoAnnuale;
    }

    public void setFatturatoAnnuale(BigDecimal fatturatoAnnuale) {
        this.fatturatoAnnuale = fatturatoAnnuale;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailContatto() {
        return emailContatto;
    }

    public void setEmailContatto(String emailContatto) {
        this.emailContatto = emailContatto;
    }

    public String getNomeContatto() {
        return nomeContatto;
    }

    public void setNomeContatto(String nomeContatto) {
        this.nomeContatto = nomeContatto;
    }

    public String getCognomeContatto() {
        return cognomeContatto;
    }

    public void setCognomeContatto(String cognomeContatto) {
        this.cognomeContatto = cognomeContatto;
    }

    public String getTelefonoContatto() {
        return telefonoContatto;
    }

    public void setTelefonoContatto(String telefonoContatto) {
        this.telefonoContatto = telefonoContatto;
    }

    public String getLogoAziendale() {
        return logoAziendale;
    }

    public void setLogoAziendale(String logoAziendale) {
        this.logoAziendale = logoAziendale;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Indirizzo getSedeLegale() {
        return sedeLegale;
    }

    public void setSedeLegale(Indirizzo sedeLegale) {
        this.sedeLegale = sedeLegale;
    }

    public Indirizzo getSedeOperativa() {
        return sedeOperativa;
    }

    public void setSedeOperativa(Indirizzo sedeOperativa) {
        this.sedeOperativa = sedeOperativa;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", ragioneSociale='" + ragioneSociale + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                ", email='" + email + '\'' +
                ", dataInserimento=" + dataInserimento +
                ", dataUltimoContatto=" + dataUltimoContatto +
                ", fatturatoAnnuale=" + fatturatoAnnuale +
                ", pec='" + pec + '\'' +
                ", telefono='" + telefono + '\'' +
                ", emailContatto='" + emailContatto + '\'' +
                ", nomeContatto='" + nomeContatto + '\'' +
                ", cognomeContatto='" + cognomeContatto + '\'' +
                ", telefonoContatto='" + telefonoContatto + '\'' +
                ", logoAziendale='" + logoAziendale + '\'' +
                ", tipoCliente=" + tipoCliente +
                ", sedeLegale=" + sedeLegale +
                ", sedeOperativa=" + sedeOperativa +
                '}';
    }
}
