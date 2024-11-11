package gruppo2.EpicEnergyServices.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

@Configuration
public class CloudinaryConfig {

    @Value("${CLOUDINARY_NAME}")
    private String cloudName;

    @Value("${CLOUDINARY_KEY}")
    private String apiKey;

    @Value("${CLOUDINARY_SECRET}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        HashMap<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }
}
