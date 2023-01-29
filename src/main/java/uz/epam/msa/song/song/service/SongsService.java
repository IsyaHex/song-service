package uz.epam.msa.song.song.service;

import uz.epam.msa.song.song.dto.ResourceDTO;
import uz.epam.msa.song.song.dto.ResourcesDTO;
import uz.epam.msa.song.song.dto.SongDTO;
import uz.epam.msa.song.song.exception.SongNotFoundException;
import uz.epam.msa.song.song.exception.SongValidationException;

public interface SongsService {
    ResourceDTO createSongRecord(SongDTO data) throws SongValidationException;
    SongDTO getSong(Integer id) throws SongNotFoundException;
    ResourcesDTO deleteResources(String ids);
}
