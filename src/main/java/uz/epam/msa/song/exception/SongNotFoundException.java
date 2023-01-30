package uz.epam.msa.song.exception;

public class SongNotFoundException extends RuntimeException {

    public SongNotFoundException() {
    }

    public SongNotFoundException(String message) {
        super(message);
    }
}
