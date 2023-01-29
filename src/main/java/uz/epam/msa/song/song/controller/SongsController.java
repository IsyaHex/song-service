package uz.epam.msa.song.song.controller;

import org.springframework.web.bind.annotation.*;
import uz.epam.msa.song.song.dto.ResourceDTO;
import uz.epam.msa.song.song.dto.ResourcesDTO;
import uz.epam.msa.song.song.dto.SongDTO;
import uz.epam.msa.song.song.exception.SongNotFoundException;
import uz.epam.msa.song.song.exception.SongValidationException;
import uz.epam.msa.song.song.service.SongsService;

@RestController
@RequestMapping("/songs")
public class SongsController {
    private final SongsService service;

    public SongsController(SongsService service) {
        this.service = service;
    }

    @PostMapping
    public ResourceDTO uploadResource(SongDTO data) throws SongValidationException {
        return service.createSongRecord(data);
    }

    @GetMapping("/{id}")
    public SongDTO getResource(@PathVariable("id") Integer id) throws SongNotFoundException {
        return service.getSong(id);
    }

    @DeleteMapping("?id={ids}")
    public ResourcesDTO deleteResources(@PathVariable String ids) throws Exception {
        if(ids.matches("((?<!^,)\\\\d+(,(?!$)|$))+") || ids.length() < 200)
            return service.deleteResources(ids);
        else
            throw new Exception("An internal server error has occurred");
    }
}
