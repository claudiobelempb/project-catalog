package br.com.surb.catalog.modules.color.request;

import java.io.Serial;
import java.io.Serializable;

public record ColorRequest(
        String name
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -7881995115976239451L;
}
