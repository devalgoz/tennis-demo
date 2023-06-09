package co.latelier.tennis.infrastructure.configuration;

import co.latelier.tennis.application.PlayerService;
import co.latelier.tennis.application.PlayerServiceImpl;
import co.latelier.tennis.infrastructure.provider.repository.PlainFilePlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ComponentScan("co.latelier.tennis")
public class ApplicationConfig {

    @Bean
    public PlayerService playerService() {
        return new PlayerServiceImpl(new PlainFilePlayerRepository());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/player/**"))
                .build();
    }

}
