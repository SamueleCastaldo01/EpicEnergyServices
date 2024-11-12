package gruppo2.EpicEnergyServices.clienti;

import gruppo2.EpicEnergyServices.clienti.Cliente;
import gruppo2.EpicEnergyServices.clienti.ClienteRepository;
import gruppo2.EpicEnergyServices.clienti.ClienteDTO;
import gruppo2.EpicEnergyServices.clienti.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

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
