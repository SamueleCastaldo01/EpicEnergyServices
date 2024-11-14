package gruppo2.EpicEnergyServices.comune;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import gruppo2.EpicEnergyServices.provincia.Provincia;
import gruppo2.EpicEnergyServices.provincia.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public List<Comune> findAll() {
        try {
            return this.comuneRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il recupero dei comuni: " + e.getMessage());
        }
    }

    public void importCSV(Reader reader) {
        try (CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] line;
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                try {
                    Provincia provincia = provinciaRepository.findByProvincia(line[3]);
                    if (provincia == null) {
                        throw new RuntimeException("Provincia non trovata per il comune: " + line[2]);
                    }
                    Comune comune = new Comune();
                    comune.setComune(line[2]);
                    comune.setProvincia(provincia);

                    comuneRepository.save(comune);
                } catch (Exception e) {
                    throw new RuntimeException("Errore durante l'importazione del comune: " + line[2] + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la lettura del file CSV: " + e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException("Errore nella validazione del file CSV: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Errore durante l'importazione del file CSV: " + e.getMessage());
        }
    }
}