package uz.epam.msa.song.song.exception;

public class SongNotFoundException extends Throwable{
    private String message;

    public SongNotFoundException() {
        message = "The song metadata with the specified id does not exist";
    }

    @Override
    public String toString() {
        return "404 - " + message;
    }
}
