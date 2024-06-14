package br.com.surb.catalog.shared.exeptions.ExeptionsResource;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record StandarError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 4425871258555747338L;

}
