package uz.epam.msa.song.exception;

public class SongValidationException extends RuntimeException {

    public SongValidationException() {
    }

    public SongValidationException(String message) {
        super(message);
    }
}
