package uz.epam.msa.song.cucumber.e2e;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class SongE2ESteps {

    @Autowired
    private TestRestTemplate template;

    private WebDriver driver;

    private String URL;

    @Given("The user opened a browser and passed {string}")
    public void app_base_url(String baseUrl) {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        log.info("Application URL -> " + baseUrl);
        URL = baseUrl;
    }

    @When("User adds id, which is {int} of a song record in the URL")
    public void add_id_to_url(Integer id) {
        driver.navigate().to(URL + id);
        log.info("Navigation URL -> " + URL + id);
    }

    @Then("User receives song metadata {string}")
    public void assert_get_metadata_response_result(String metadata) {
        String res = driver.getPageSource();
        log.info(System.lineSeparator(), "Page content -> " + res, System.lineSeparator());
        assertTrue(res.contains("name") || res.contains("artist"));
        driver.close();
    }

}
