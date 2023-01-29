package uz.epam.msa.song.song.exception;

public class SongValidationException extends Throwable {
    private String message;

    public SongValidationException() {
        message = "Song metadata missing validation error";
    }

    @Override
    public String toString() {
        return "400 - " + message;
    }
}
