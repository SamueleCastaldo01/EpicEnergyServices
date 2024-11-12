package gruppo2.EpicEnergyServices.clienti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class ClienteDTO {

    private Long id;
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
    private TipoCliente tipoCliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Service
    public static class ClienteService {

        @Autowired
        private ClienteRepository clienteRepository;

        public Page<ClienteDTO> getClienti(Pageable pageable) {
            return clienteRepository.findAll(pageable).map(this::convertToDTO);
        }

        public ClienteDTO getClienteById(Long id) {
            return clienteRepository.findById(id).map(this::convertToDTO).orElse(null);
        }

        public ClienteDTO createCliente(ClienteDTO clienteDTO) {
            Cliente cliente = convertToEntity(clienteDTO);
            cliente = clienteRepository.save(cliente);
            return convertToDTO(cliente);
        }

        public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
            Optional<Cliente> existingCliente = clienteRepository.findById(id);
            if (existingCliente.isPresent()) {
                Cliente cliente = existingCliente.get();
                cliente.setRagioneSociale(clienteDTO.getRagioneSociale());
                cliente.setPartitaIva(clienteDTO.getPartitaIva());
                cliente.setEmail(clienteDTO.getEmail());
                cliente.setDataInserimento(clienteDTO.getDataInserimento());
                cliente.setDataUltimoContatto(clienteDTO.getDataUltimoContatto());
                cliente.setFatturatoAnnuale(clienteDTO.getFatturatoAnnuale());
                cliente.setPec(clienteDTO.getPec());
                cliente.setTelefono(clienteDTO.getTelefono());
                cliente.setEmailContatto(clienteDTO.getEmailContatto());
                cliente.setNomeContatto(clienteDTO.getNomeContatto());
                cliente.setCognomeContatto(clienteDTO.getCognomeContatto());
                cliente.setTelefonoContatto(clienteDTO.getTelefonoContatto());
                cliente.setLogoAziendale(clienteDTO.getLogoAziendale());
                cliente.setTipoCliente(clienteDTO.getTipoCliente());
                cliente = clienteRepository.save(cliente);
                return convertToDTO(cliente);
            }
            return null;
        }

        public void deleteCliente(Long id) {
            clienteRepository.deleteById(id);
        }

        private ClienteDTO convertToDTO(Cliente cliente) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setRagioneSociale(cliente.getRagioneSociale());
            clienteDTO.setPartitaIva(cliente.getPartitaIva());
            clienteDTO.setEmail(cliente.getEmail());
            clienteDTO.setDataInserimento(cliente.getDataInserimento());
            clienteDTO.setDataUltimoContatto(cliente.getDataUltimoContatto());
            clienteDTO.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
            clienteDTO.setPec(cliente.getPec());
            clienteDTO.setTelefono(cliente.getTelefono());
            clienteDTO.setEmailContatto(cliente.getEmailContatto());
            clienteDTO.setNomeContatto(cliente.getNomeContatto());
            clienteDTO.setCognomeContatto(cliente.getCognomeContatto());
            clienteDTO.setTelefonoContatto(cliente.getTelefonoContatto());
            clienteDTO.setLogoAziendale(cliente.getLogoAziendale());
            clienteDTO.setTipoCliente(cliente.getTipoCliente());
            return clienteDTO;
        }

        private Cliente convertToEntity(ClienteDTO clienteDTO) {
            Cliente cliente = new Cliente();
            cliente.setRagioneSociale(clienteDTO.getRagioneSociale());
            cliente.setPartitaIva(clienteDTO.getPartitaIva());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setDataInserimento(clienteDTO.getDataInserimento());
            cliente.setDataUltimoContatto(clienteDTO.getDataUltimoContatto());
            cliente.setFatturatoAnnuale(clienteDTO.getFatturatoAnnuale());
            cliente.setPec(clienteDTO.getPec());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setEmailContatto(clienteDTO.getEmailContatto());
            cliente.setNomeContatto(clienteDTO.getNomeContatto());
            cliente.setCognomeContatto(clienteDTO.getCognomeContatto());
            cliente.setTelefonoContatto(clienteDTO.getTelefonoContatto());
            cliente.setLogoAziendale(clienteDTO.getLogoAziendale());
            cliente.setTipoCliente(clienteDTO.getTipoCliente());
            return cliente;
        }
    }
}
