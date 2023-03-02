package uz.epam.msa.song.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.epam.msa.song.constant.Constants;
import uz.epam.msa.song.domain.Song;
import uz.epam.msa.song.dto.DeletedSongsDTO;
import uz.epam.msa.song.dto.ResourceDTO;
import uz.epam.msa.song.dto.SongDTO;
import uz.epam.msa.song.exception.SongNotFoundException;
import uz.epam.msa.song.exception.SongValidationException;
import uz.epam.msa.song.repository.SongsRepository;
import uz.epam.msa.song.service.SongsService;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongsServiceImpl implements SongsService {

    private final SongsRepository repository;
    private final ModelMapper mapper;

    public SongsServiceImpl(SongsRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResourceDTO createSongRecord(SongDTO data) throws SongValidationException {
        Song song;
        try {
            song = mapper.map(data, Song.class);
            song.setDeleted(false);
        } catch (Exception e) {
            throw new SongValidationException(Constants.VALIDATION_EXCEPTION);
        }
        return mapper.map(repository.save(song), ResourceDTO.class);
    }

    @Override
    public SongDTO getSong(Integer id) throws SongNotFoundException {
        return repository.findById(id)
                .filter(song -> !song.getDeleted())
                .map(song -> mapper.map(song, SongDTO.class))
                .orElseThrow(() -> new SongNotFoundException(Constants.SONG_NOT_FOUND_EXCEPTION));
    }

    @Override
    public DeletedSongsDTO deleteSongs(String ids) {
        DeletedSongsDTO dto = new DeletedSongsDTO();
        dto.setIds(Arrays.stream(ids.split(Constants.COMMA_REGEX))
                .filter(id -> id.matches(Constants.NUMBER_REGEX))
                .map(id -> repository.findById(Integer.parseInt(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(file -> !file.getDeleted())
                .peek(file -> file.setDeleted(true))
                .map(repository::save)
                .map(Song::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
