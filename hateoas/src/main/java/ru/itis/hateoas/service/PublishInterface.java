package ru.itis.hateoas.service;

import org.springframework.expression.AccessException;

public interface PublishInterface<T> {

    public T publish(T model) throws AccessException;

}
