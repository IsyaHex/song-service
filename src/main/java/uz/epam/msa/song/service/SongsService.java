package uz.epam.msa.song.service;

import uz.epam.msa.song.dto.DeletedResourcesDTO;
import uz.epam.msa.song.dto.ResourceDTO;
import uz.epam.msa.song.dto.SongDTO;
import uz.epam.msa.song.exception.SongNotFoundException;
import uz.epam.msa.song.exception.SongValidationException;

public interface SongsService {
    ResourceDTO createSongRecord(SongDTO data) throws SongValidationException;
    SongDTO getSong(Integer id) throws SongNotFoundException;
    DeletedResourcesDTO deleteResources(String ids);
}
