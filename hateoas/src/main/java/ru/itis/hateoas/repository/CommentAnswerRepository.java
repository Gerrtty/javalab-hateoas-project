package ru.itis.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.model.CommentAnswer;

@RepositoryRestResource
public interface CommentAnswerRepository extends JpaRepository<CommentAnswer, Long> {

}