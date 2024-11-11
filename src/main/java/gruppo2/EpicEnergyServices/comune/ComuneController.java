package gruppo2.EpicEnergyServices.comune;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;

@RestController
@RequestMapping("/comune")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @PostMapping("/import-csv")
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
