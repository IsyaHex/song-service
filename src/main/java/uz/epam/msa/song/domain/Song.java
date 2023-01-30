package uz.epam.msa.song.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String artist;
    private String album;
    private String length;
    private String year;
    private String resourceId;
    private Boolean deleted;
}
