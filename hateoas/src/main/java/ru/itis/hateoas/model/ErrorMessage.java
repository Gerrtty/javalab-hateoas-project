package ru.itis.hateoas.model;

public class ErrorMessage<T> {

    private T model;
    private String message;

    public ErrorMessage(T model, String message) {
       this.message = message;
       this.model = model;
    }

}
