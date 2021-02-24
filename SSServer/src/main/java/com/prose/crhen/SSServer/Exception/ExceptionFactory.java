package com.prose.crhen.SSServer.Exception;

public class ExceptionFactory {

    private ExceptionFactory() {
    }

    public static ServerStorageException create(String errorMessage) {
        return new ServerStorageException(errorMessage);
    }

    public static ServerStorageException create(String messageTemplate, Throwable cause, Object... args) {
        return new ServerStorageException(String.format(messageTemplate, args), cause);
    }

    public static ServerStorageException create(String messageTemplate, Object... args) {
        return new ServerStorageException( String.format(messageTemplate, args));
    }

}
