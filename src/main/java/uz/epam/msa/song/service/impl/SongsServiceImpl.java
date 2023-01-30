package uz.epam.msa.song.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.epam.msa.song.constant.ExceptionConstants;
import uz.epam.msa.song.dto.DeletedResourcesDTO;
import uz.epam.msa.song.dto.ResourceDTO;
import uz.epam.msa.song.dto.SongDTO;
import uz.epam.msa.song.exception.InternalServerError;
import uz.epam.msa.song.exception.SongNotFoundException;
import uz.epam.msa.song.exception.SongValidationException;
import uz.epam.msa.song.repository.SongsRepository;
import uz.epam.msa.song.service.SongsService;
import uz.epam.msa.song.domain.Song;

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
        Song song = new Song();
        try {
            song.setName(data.getName());
            song.setArtist(data.getArtist());
            song.setAlbum(data.getAlbum());
            song.setLength(data.getLength());
            song.setYear(data.getYear());
            song.setResourceId(data.getResourceId());

        } catch (Exception e) {
            throw new SongValidationException(ExceptionConstants.VALIDATION_EXCEPTION);
        }
        return mapper.map(repository.save(song), ResourceDTO.class);
    }

    @Override
    public SongDTO getSong(Integer id) throws SongNotFoundException {
        return repository.findById(id)
                .map(song -> mapper.map(song, SongDTO.class))
                .orElseThrow(() -> new SongNotFoundException(ExceptionConstants.RESOURCE_NOT_FOUND_EXCEPTION));
    }

    @Override
    public DeletedResourcesDTO deleteResources(String ids) {
        if(!ids.matches("((?<!^,)\\\\d+(,(?!$)|$))+"))
            throw new InternalServerError(ExceptionConstants.INTERNAL_SERVER_ERROR);
        DeletedResourcesDTO dto = new DeletedResourcesDTO();
        dto.setIds(Arrays.stream(ids.split(","))
                .filter(id -> id.matches("[0-9]"))
                .map(id -> repository.findById(Integer.parseInt(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .peek(file -> file.setDeleted(true))
                .map(repository::save)
                .map(Song::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
