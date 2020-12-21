package ru.itis.hateoas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Subscription extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private BlogUser who;

    @ManyToOne(fetch = FetchType.LAZY)
    private BlogUser whom;

}
