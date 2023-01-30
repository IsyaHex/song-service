package uz.epam.msa.song.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.epam.msa.song.dto.DeletedResourcesDTO;
import uz.epam.msa.song.dto.ResourceDTO;
import uz.epam.msa.song.dto.SongDTO;
import uz.epam.msa.song.exception.SongNotFoundException;
import uz.epam.msa.song.exception.SongValidationException;
import uz.epam.msa.song.service.SongsService;

@RestController
@RequestMapping("/songs")
public class SongsController {
    private final SongsService service;

    public SongsController(SongsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResourceDTO> uploadResource(SongDTO data) throws SongValidationException {
        ResourceDTO dto = service.createSongRecord(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public SongDTO getResource(@PathVariable("id") Integer id) throws SongNotFoundException {
        return service.getSong(id);
    }

    @DeleteMapping("?id={ids}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DeletedResourcesDTO deleteResources(@PathVariable String ids) throws Exception {
        return service.deleteResources(ids);
    }
}
