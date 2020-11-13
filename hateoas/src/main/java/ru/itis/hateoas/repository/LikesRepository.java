package ru.itis.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoas.model.PostLike;

@RepositoryRestResource
public interface LikesRepository extends JpaRepository<PostLike, Long> {

}

