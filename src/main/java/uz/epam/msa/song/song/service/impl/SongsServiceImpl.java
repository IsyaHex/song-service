package uz.epam.msa.song.song.service.impl;

import org.springframework.stereotype.Service;
import uz.epam.msa.song.song.dto.ResourceDTO;
import uz.epam.msa.song.song.dto.ResourcesDTO;
import uz.epam.msa.song.song.dto.SongDTO;
import uz.epam.msa.song.song.exception.SongNotFoundException;
import uz.epam.msa.song.song.exception.SongValidationException;
import uz.epam.msa.song.song.repository.SongsRepository;
import uz.epam.msa.song.song.service.SongsService;
import uz.epam.msa.song.song.service.entity.Song;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongsServiceImpl implements SongsService {

    private final SongsRepository repository;

    public SongsServiceImpl(SongsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResourceDTO createSongRecord(SongDTO data) throws SongValidationException {
        validation(data);
        Song song = new Song();
        song.setName(data.getName());
        song.setArtist(data.getArtist());
        song.setAlbum(data.getAlbum());
        song.setLength(data.getLength());
        song.setYear(data.getYear());
        song.setResourceId(data.getResourceId());
        Song saved = repository.save(song);

        // sends data to resource-microservice to return on ui
        return new ResourceDTO(saved.getId());
    }

    @Override
    public SongDTO getSong(Integer id) throws SongNotFoundException {
        Song song = repository.findById(id).orElseThrow(SongNotFoundException::new);
        SongDTO dto = new SongDTO();
        dto.setName(song.getName());
        dto.setArtist(song.getArtist());
        dto.setAlbum(song.getAlbum());
        dto.setLength(song.getLength());
        dto.setYear(song.getYear());
        dto.setResourceId(song.getResourceId());

        return dto;
    }

    @Override
    public ResourcesDTO deleteResources(String ids) {
        List<Integer> list = Arrays.stream(ids.split(",")).map(Integer::valueOf).collect(Collectors.toList());
        repository.deleteAllById(list);
        return new ResourcesDTO(list.toArray(Integer[]::new));
    }


    private void validation(SongDTO data) throws SongValidationException {
        if(data.getName() == null || data.getArtist() == null
                || data.getAlbum() == null || data.getLength() == null
                || data.getYear() == null || data.getResourceId() == null) {
            throw new SongValidationException();
        }
    }
}
