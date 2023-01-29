package uz.epam.msa.song.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.epam.msa.song.song.service.entity.Song;

@Repository
public interface SongsRepository extends JpaRepository<Song, Integer> {
}
