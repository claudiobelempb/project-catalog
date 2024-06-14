package br.com.surb.catalog.shared.exeptions.ExeptionsService;

public class AppDataIntegrityViolationException extends RuntimeException{
    public AppDataIntegrityViolationException(String message) {
        super(message);
    }
}
