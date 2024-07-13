package br.com.surb.catalog.shared.exeptions;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;


@Builder
public record FieldMessage(
        String fieldName,
        String message
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -6040426973536233384L;
}
