package ru.itis.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoas.model.BlogUser;

@RepositoryRestResource
public interface BlogUsersRepository extends JpaRepository<BlogUser, Long> {

}
