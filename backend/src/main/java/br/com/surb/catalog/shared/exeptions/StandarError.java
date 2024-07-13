package br.com.surb.catalog.shared.exeptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandarError implements Serializable {
    @Serial
    private static final long serialVersionUID = 4425871258555747338L;

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}
