package gruppo2.EpicEnergyServices.provincia;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import gruppo2.EpicEnergyServices.comune.Comune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public List<Provincia> findAll() {
        try {
            return this.provinciaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il recupero delle province: " + e.getMessage());
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
                    Provincia provincia = new Provincia();

                    if (Objects.equals(line[1], "Verbania")) {
                        provincia.setProvincia("Verbano-Cusio-Ossola");

                    } else if (Objects.equals(line[1], "Monza-Brianza")) {
                        provincia.setProvincia("Monza e della Brianza");

                    } else if (Objects.equals(line[1], "Aosta")) {
                        provincia.setProvincia("Valle d'Aosta/Vallée d'Aoste");

                    } else if (Objects.equals(line[1], "Bolzano")) {
                        provincia.setProvincia("Bolzano/Bozen");

                    } else if (Objects.equals(line[1], "La-Spezia")) {
                        provincia.setProvincia("La Spezia");

                    } else if (Objects.equals(line[1], "Reggio-Emilia")) {
                        provincia.setProvincia("Reggio nell'Emilia");

                    } else if (Objects.equals(line[1], "Forli-Cesena")) {
                        provincia.setProvincia("Forlì-Cesena");

                    } else if (Objects.equals(line[1], "Pesaro-Urbino")) {
                        provincia.setProvincia("Pesaro e Urbino");

                    } else if (Objects.equals(line[1], "Ascoli-Piceno")) {
                        provincia.setProvincia("Ascoli Piceno");

                    } else if (Objects.equals(line[1], "Vibo-Valentia")) {
                        provincia.setProvincia("Vibo Valentia");

                    } else if (Objects.equals(line[1], "Reggio-Calabria")) {
                        provincia.setProvincia("Reggio Calabria");

                    } else {
                        provincia.setProvincia(line[1]);
                    }

                    provincia.setSigla(line[0]);
                    provincia.setRegione(line[2]);

                    provinciaRepository.save(provincia);

                } catch (Exception e) {
                    System.err.println("Errore durante l'elaborazione della riga: " + e.getMessage());
                }
            }

            Provincia provincia = new Provincia("Sud Sardegna", "Sardegna", "SU");
            provinciaRepository.save(provincia);

        } catch (IOException e) {
            throw new RuntimeException("Errore durante la lettura del file CSV: " + e.getMessage());
        } catch (CsvValidationException e) {
            throw new RuntimeException("Errore durante la validazione del file CSV: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Errore durante l'importazione del file CSV: " + e.getMessage());
        }
    }
}
