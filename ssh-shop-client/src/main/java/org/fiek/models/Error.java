package org.fiek.models;

public class Error {

    final String message;
    final int statusCode;
    final String name;

    public Error(String message, int statusCode, String name) {
        this.message = message;
        this.statusCode = statusCode;
        this.name = name;
    }

    public Error() {
        this("", 500, "");
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getName() {
        return name;
    }
}
