package ru.itis.hateoas.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.AccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.interfaces.PublishInt;
import ru.itis.hateoas.interfaces.WithAuthorInt;
import ru.itis.hateoas.model.Post;

@Service
public class PublishService<T> implements PublishInt<T> {

    public ResponseEntity<?> publish(T model, JpaRepository<T, Long> repository) {
        try {
            return ResponseEntity.ok(EntityModel.of(save(model, repository)));
        } catch (AccessException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(model);
    }

    public T save(T model, JpaRepository<T, Long> repository) throws AccessException {
        WithAuthorInt interModel = (WithAuthorInt) model;

        if (model != null && interModel.getAuthor() != null && interModel.getAuthor().isConfirmed()) {
            return repository.save(model);
        }

        throw new AccessException("User not confirmed");
    }

}
