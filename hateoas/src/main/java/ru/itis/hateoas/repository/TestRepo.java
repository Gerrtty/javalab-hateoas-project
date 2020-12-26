package ru.itis.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoas.model.TestModel;

@RepositoryRestResource
public interface TestRepo extends JpaRepository<TestModel, Long> {
}
