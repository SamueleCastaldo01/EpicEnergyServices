package gruppo2.EpicEnergyServices.clienti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<ClienteDTO> getClienti(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(this::convertToDTO);
    }

    public ClienteDTO getClienteById(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente non trovato con ID: " + id));
    }

    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return convertToDTO(cliente);
    }

    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente non trovato con ID: " + id));

        cliente.setRagioneSociale(clienteDTO.ragioneSociale());
        cliente.setPartitaIva(clienteDTO.partitaIva());
        cliente.setEmail(clienteDTO.email());
        cliente.setDataInserimento(clienteDTO.dataInserimento());
        cliente.setDataUltimoContatto(clienteDTO.dataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteDTO.fatturatoAnnuale());
        cliente.setPec(clienteDTO.pec());
        cliente.setTelefono(clienteDTO.telefono());
        cliente.setEmailContatto(clienteDTO.emailContatto());
        cliente.setNomeContatto(clienteDTO.nomeContatto());
        cliente.setCognomeContatto(clienteDTO.cognomeContatto());
        cliente.setTelefonoContatto(clienteDTO.telefonoContatto());
        cliente.setLogoAziendale(clienteDTO.logoAziendale());
        cliente.setTipoCliente(clienteDTO.tipoCliente());

        cliente = clienteRepository.save(cliente);
        return convertToDTO(cliente);
    }

    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente non trovato con ID: " + id));
        clienteRepository.delete(cliente);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getRagioneSociale(),
                cliente.getPartitaIva(),
                cliente.getEmail(),
                cliente.getDataInserimento(),
                cliente.getDataUltimoContatto(),
                cliente.getFatturatoAnnuale(),
                cliente.getPec(),
                cliente.getTelefono(),
                cliente.getEmailContatto(),
                cliente.getNomeContatto(),
                cliente.getCognomeContatto(),
                cliente.getTelefonoContatto(),
                cliente.getLogoAziendale(),
                cliente.getTipoCliente()
        );
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(clienteDTO.ragioneSociale());
        cliente.setPartitaIva(clienteDTO.partitaIva());
        cliente.setEmail(clienteDTO.email());
        cliente.setDataInserimento(clienteDTO.dataInserimento());
        cliente.setDataUltimoContatto(clienteDTO.dataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteDTO.fatturatoAnnuale());
        cliente.setPec(clienteDTO.pec());
        cliente.setTelefono(clienteDTO.telefono());
        cliente.setEmailContatto(clienteDTO.emailContatto());
        cliente.setNomeContatto(clienteDTO.nomeContatto());
        cliente.setCognomeContatto(clienteDTO.cognomeContatto());
        cliente.setTelefonoContatto(clienteDTO.telefonoContatto());
        cliente.setLogoAziendale(clienteDTO.logoAziendale());
        cliente.setTipoCliente(clienteDTO.tipoCliente());
        return cliente;
    }
}
