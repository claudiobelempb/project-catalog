package br.com.surb.catalog.shared.exeptions.ExeptionsService;

public class AppIllegalArgumentException extends RuntimeException {
    public AppIllegalArgumentException(String msg) {
        super(msg);
    }
}