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

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    ProvinciaRepository provinciaRepository;

    public void importCSV(Reader reader) {
        try (CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] line;
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {
                Provincia provincia = provinciaRepository.findByProvincia(line[3]);
                Comune comune = new Comune();
                comune.setComune(line[2]);
                comune.setProvincia(provincia);

                comuneRepository.save(comune);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
