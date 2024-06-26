package br.com.surb.catalog.shared.exeptions.ExeptionsResource;

public class AppResourceNotFondExecption extends RuntimeException {
    public AppResourceNotFondExecption(String msg) {
        super(msg);
    }
}
