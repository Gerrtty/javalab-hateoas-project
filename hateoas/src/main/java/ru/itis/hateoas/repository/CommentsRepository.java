package ru.itis.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.model.Comment;

@RepositoryRestResource
public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
