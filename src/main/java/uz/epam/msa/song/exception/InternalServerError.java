package uz.epam.msa.song.exception;

public class InternalServerError extends Error {


    public InternalServerError() {
    }

    public InternalServerError(String message) {
        super(message);
    }
}
