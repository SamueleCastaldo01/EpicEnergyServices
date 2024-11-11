package gruppo2.EpicEnergyServices.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;

@RestController
@RequestMapping("/province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @PostMapping("/import-csv")
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
