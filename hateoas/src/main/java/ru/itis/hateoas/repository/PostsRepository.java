package ru.itis.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoas.model.Post;

@RepositoryRestResource
public interface PostsRepository extends JpaRepository<Post, Long> {

}
