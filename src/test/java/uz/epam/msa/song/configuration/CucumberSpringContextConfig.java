package uz.epam.msa.song.configuration;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import uz.epam.msa.song.SongServiceApplication;

@CucumberContextConfiguration
@TestPropertySource(locations = "classpath:application-integration-test.properties")
@SpringBootTest(classes = SongServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringContextConfig {

}
