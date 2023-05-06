package uz.epam.msa.song.service;

import uz.epam.msa.song.dto.DeletedSongsDTO;
import uz.epam.msa.song.dto.ResourceDTO;
import uz.epam.msa.song.dto.SongDTO;
import uz.epam.msa.song.exception.SongNotFoundException;
import uz.epam.msa.song.exception.SongValidationException;

public interface SongsService {
    ResourceDTO createSongRecord(SongDTO data, boolean processorCall) throws SongValidationException;
    SongDTO getSong(Integer id) throws SongNotFoundException;
    DeletedSongsDTO deleteSongs(String ids);
}
