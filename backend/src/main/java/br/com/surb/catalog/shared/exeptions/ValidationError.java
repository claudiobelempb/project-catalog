package br.com.surb.catalog.shared.exeptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandarError {
    private final List<FieldMessage> erros = new ArrayList<>();

    public void addErros(String fieldName, String message) {
        erros.add(new FieldMessage(fieldName, message));
    }

}
