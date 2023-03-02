package uz.epam.msa.song.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import uz.epam.msa.song.SongServiceApplication;
import uz.epam.msa.song.domain.Song;
import uz.epam.msa.song.dto.DeletedSongsDTO;
import uz.epam.msa.song.dto.ResourceDTO;
import uz.epam.msa.song.entity.SongDTO;
import uz.epam.msa.song.repository.SongsRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SongServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class SongsIntegrationTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate template;

    @Autowired
    private SongsRepository repository;

    private final String URL = "http://localhost:1199/songs";

    @BeforeEach
    void setUp() {
        Song song = new Song();
        song.setId(1);
        song.setName("test");
        song.setLength("99999");
        song.setAlbum("test-album");
        song.setArtist("test-artist");
        song.setYear("1995-12-06");
        song.setResourceId("99999");
        song.setDeleted(false);
        repository.save(song);
    }

    @Test
    void uploadSong() {
        ResponseEntity<ResourceDTO> response = template.postForEntity(URL, getDto(), ResourceDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getSong() {
        ResponseEntity<SongDTO> response = template.getForEntity(URL + "/1", SongDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteSongs() {
        ResponseEntity<DeletedSongsDTO> responseEntity = template.exchange(URL + "?id=1",
                HttpMethod.DELETE, HttpEntity.EMPTY, DeletedSongsDTO.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private SongDTO getDto() {
        SongDTO dto = new SongDTO();
        dto.setName("test");
        dto.setLength("99999");
        dto.setAlbum("test-album");
        dto.setArtist("test-artist");
        dto.setYear("1995-12-06");
        dto.setResourceId("99999");

        return dto;
    }
}