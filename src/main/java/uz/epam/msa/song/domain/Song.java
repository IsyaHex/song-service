package uz.epam.msa.song.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "song_seq", sequenceName = "seq_song", allocationSize = 1)
    private Integer id;

    @NotNull
    private String name;
    @NotNull
    private String artist;
    @NotNull
    private String album;
    private String length;
    private String year;
    private String resourceId;
    private Boolean deleted;
}
