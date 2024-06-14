package br.com.surb.catalog.shared.exeptions.ExeptionsService;

public class AppEntityNotFoundException extends RuntimeException{
    public AppEntityNotFoundException(String message) {
        super(message);
    }
}
