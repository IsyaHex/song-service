package uz.epam.msa.song.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.epam.msa.song.dto.DeletedSongsDTO;
import uz.epam.msa.song.dto.ResourceDTO;
import uz.epam.msa.song.dto.SongDTO;
import uz.epam.msa.song.exception.SongNotFoundException;
import uz.epam.msa.song.exception.SongValidationException;
import uz.epam.msa.song.service.SongsService;

import javax.validation.constraints.Max;

@RestController
@RequestMapping("/songs")
public class SongsController {
    private final SongsService service;

    public SongsController(SongsService service) {
        this.service = service;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceDTO> uploadResource(@RequestBody SongDTO data) throws SongValidationException {
        ResourceDTO dto = service.createSongRecord(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SongDTO getResource(@PathVariable("id") Integer id) throws SongNotFoundException {
        return service.getSong(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public DeletedSongsDTO deleteResources(@RequestParam(value = "id") @Max(200) String ids) {
        return service.deleteSongs(ids);
    }
}
