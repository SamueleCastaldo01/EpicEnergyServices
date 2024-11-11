package gruppo2.EpicEnergyServices.provincia;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importCSV(Reader reader) {
        try (CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] line;
            csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                Provincia provincia = new Provincia();
                provincia.setSigla(line[0]);
                provincia.setProvincia(line[1]);
                provincia.setRegione(line[2]);

                provinciaRepository.save(provincia);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
}
}
