package uz.epam.msa.song.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SongDTO implements Serializable {
    private String name;
    private String artist;
    private String album;
    private String length;
    private String year;
    private String resourceId;
}
