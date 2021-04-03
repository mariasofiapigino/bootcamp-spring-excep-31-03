package com.segundaparte.linktracker.exceptionHandler;

public class LinkException extends Exception{
    public static final String LINK_NOT_FOUND = "LINK_NOT_FOUND";
    public static final String LINK_NOT_FOUND_MSG = "El link no se ha encontrado";
    public static final String ID_ALREADY_EXISTS = "ID_ALREADY_EXISTS";
    public static final String ID_ALREADY_EXISTS_MSG = "El ID que se ha generado ya existe, intentelo nuevamente";
    public static final String URL_NOT_VALID = "URL_NOT_VALID";
    public static final String URL_NOT_VALID_MSG = "La URL no es válida";
    public static final String PASSWORD_INVALID = "PASSWORD_INVALID";
    public static final String PASSWORD_INVALID_MSG = "La contraseña no es correcta";

    private String code;

    public LinkException(String code, String message)
    {
        super(message);
        this.code = code;
    }
}
