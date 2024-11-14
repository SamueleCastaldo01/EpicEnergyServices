package gruppo2.EpicEnergyServices.provincia;

import gruppo2.EpicEnergyServices.comune.Comune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<Provincia> findAll() {
        try {
            return this.provinciaService.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il recupero delle province: " + e.getMessage());
        }
    }

    @PostMapping("/import-csv")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String importCSV(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Il file non può essere vuoto.");
            }

            provinciaService.importCSV(new InputStreamReader(file.getInputStream()));
            return "Importazione completata!";
        } catch (IllegalArgumentException e) {
            return "Errore: " + e.getMessage();
        } catch (IOException e) {
            return "Errore durante la lettura del file: " + e.getMessage();
        } catch (Exception e) {
            return "Errore durante l'importazione: " + e.getMessage();
        }
    }
}