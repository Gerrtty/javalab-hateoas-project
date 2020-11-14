package ru.itis.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoas.model.Post;

import java.util.List;

@RepositoryRestResource
public interface PostsRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTitle(String title);

}
