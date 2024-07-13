package br.com.surb.catalog.shared.utils;

import br.com.surb.catalog.shared.exeptions.FieldMessage;
import io.micrometer.common.util.StringUtils;

import java.util.List;

import static br.com.surb.catalog.shared.constants.ValidatorConstants.REQUIRED_FIELD;

public final class ValidationUtils {

    public static void requerid(String field, List<FieldMessage> list) {
        if (StringUtils.isBlank(field)) {
            list.add(new FieldMessage(field, REQUIRED_FIELD));
        }
    }

}
