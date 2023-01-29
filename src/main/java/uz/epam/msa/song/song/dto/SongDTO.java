package uz.epam.msa.song.song.dto;

import lombok.Data;

@Data
public class SongDTO {
    private String name;
    private String artist;
    private String album;
    private String length;
    private String year;
    private String resourceId;
}
