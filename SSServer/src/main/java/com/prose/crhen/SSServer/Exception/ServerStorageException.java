package com.prose.crhen.SSServer.Exception;

public class ServerStorageException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public ServerStorageException(String message) {
        super(message);
    }

    public ServerStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
