package udemy.apiawsmysql.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setEmail("gavapim@gmail.com");
        contact.setName("Gabriel Vasconcellos");
        contact.setUrl("https://github.com/gvpodev");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Spring with AWS API")
                .version("v1")
                .contact(contact)
                .license(mitLicense)
                .description("Spring Boot 3 with Docker, MySQL and AWS");

        return new OpenAPI().info(info);
    }
}
