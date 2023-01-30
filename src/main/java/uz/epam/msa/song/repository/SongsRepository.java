package uz.epam.msa.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.epam.msa.song.domain.Song;

@Repository
public interface SongsRepository extends JpaRepository<Song, Integer> {
}
