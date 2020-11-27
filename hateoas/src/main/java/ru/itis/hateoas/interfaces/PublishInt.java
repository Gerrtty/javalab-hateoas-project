package ru.itis.hateoas.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.AccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoas.service.PublishInterface;

public interface PublishInt<T> {
    ResponseEntity<?> publish(T model, JpaRepository<T, Long> repository);

    T save(T model, JpaRepository<T, Long> repository) throws AccessException;
}
