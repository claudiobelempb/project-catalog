package br.com.surb.catalog.shared.constants;

public final class ValidatorConstants {
    public static final String DUPLICATE = ".duplicate";

    public static final String REQUIRED_FIELD = ".Campo obrigatório";
    public static final String REQUIRED_EMAIL = ".Favor entrar um email válido";
    public static final String REQUIRED_EMAIL_EXIST = ".Email já existe";
    public static final String REQUIRED_NAME_EXIST = ".Nome já existe";
    public static final String REQUIRED_PRICE_POSITIVO = ".Preço deve ser um valor positivo";
    public static final String REQUIRED_DATA_PRESENT = ".A data do produto não pode ser futura";
    public static final Integer REQUIRED_SIZE_MIN = 5;
    public static final Integer REQUIRED_SIZE_MAX = 60;
    public static final String REQUIRED_SIZE_60 = ".Deve ter entre 5 e 60 caracteres";
    public static final String REQUIRED_SIZE_16 = ".Deve ter entre 5 e 16 caracteres";
    public static final String VIOLATION_ERROR = ".Validation error";

    private ValidatorConstants(){}
}
