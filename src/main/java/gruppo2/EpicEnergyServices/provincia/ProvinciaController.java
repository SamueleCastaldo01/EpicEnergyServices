package gruppo2.EpicEnergyServices.provincia;

import gruppo2.EpicEnergyServices.comune.Comune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return this.provinciaService.findAll();
    }

    @PostMapping("/import-csv")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String importCSV(@RequestParam("file") MultipartFile file) {
        try {
            provinciaService.importCSV(new InputStreamReader(file.getInputStream()));
            return "Importazione completata!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore durante l'importazione: " + e.getMessage();
        }
    }
}
