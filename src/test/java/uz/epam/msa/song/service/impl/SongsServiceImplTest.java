package uz.epam.msa.song.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uz.epam.msa.song.domain.Song;
import uz.epam.msa.song.dto.DeletedSongsDTO;
import uz.epam.msa.song.dto.ResourceDTO;
import uz.epam.msa.song.dto.SongDTO;
import uz.epam.msa.song.repository.SongsRepository;
import uz.epam.msa.song.service.SongsService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongsServiceImplTest {

    private SongsService service;

    @Mock
    private ModelMapper mapper;

    @Mock
    private SongsRepository repository;

    @BeforeEach
    void init() {
        service = new SongsServiceImpl(repository, mapper);
    }

    @Test
    void createSongRecord() {
        SongDTO dto = getDto();
        Song song = getSampleSong();
        ResourceDTO resource = new ResourceDTO();
        resource.setId(song.getId());

        when(mapper.map(dto, Song.class)).thenReturn(song);
        when(repository.save(song)).thenReturn(song);
        when(mapper.map(repository.save(song), ResourceDTO.class)).thenReturn(resource);

        assertEquals(resource, service.createSongRecord(dto));
    }

    @Test
    void getSong() {
        SongDTO dto = getDto();
        Song song = getSampleSong();
        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(song));
        when(mapper.map(song, SongDTO.class)).thenReturn(dto);

        assertEquals(dto, service.getSong(any(Integer.class)));
    }

    @Test
    void deleteSongs() {
        Song song = getSampleSong();
        List<Integer> ids = List.of(99999);
        DeletedSongsDTO dto = new DeletedSongsDTO();
        dto.setIds(ids);

        when(repository.findById(any(Integer.class))).thenReturn(Optional.of(song));
        when(repository.save(song)).thenReturn(song);
        assertEquals(dto, service.deleteSongs("99999"));
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

    private Song getSampleSong() {
        Song song = new Song();
        song.setId(99999);
        song.setName("test");
        song.setLength("99999");
        song.setAlbum("test-album");
        song.setArtist("test-artist");
        song.setYear("1995-12-06");
        song.setResourceId("99999");
        song.setDeleted(false);

        return song;
    }
}