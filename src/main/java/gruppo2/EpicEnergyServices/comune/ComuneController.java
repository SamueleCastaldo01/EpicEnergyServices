package gruppo2.EpicEnergyServices.comune;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/comune")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<Comune> findAll() {
        try {
            return this.comuneService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il recupero dei comuni: " + e.getMessage());
        }
    }

    @GetMapping("/provincia")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<Comune> getComuniByProvincia(@RequestParam String nomeProvincia) {
        return comuneService.trovaComuniPerProvincia(nomeProvincia);
    }

    @PostMapping("/import-csv")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String importCSV(@RequestParam("file") MultipartFile file) {
        try {
            comuneService.importCSV(new InputStreamReader(file.getInputStream()));
            return "Importazione completata!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore durante l'importazione: " + e.getMessage();
        }
    }
}
