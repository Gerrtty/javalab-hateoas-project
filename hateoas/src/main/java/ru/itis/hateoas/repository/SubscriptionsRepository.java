package ru.itis.hateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoas.model.Subscription;

@RepositoryRestResource
public interface SubscriptionsRepository extends JpaRepository<Subscription, Long> {
}