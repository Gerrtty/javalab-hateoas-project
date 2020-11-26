package ru.itis.hateoas.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BlogUser extends AbstractEntity {

    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private boolean isConfirmed;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private List<Post> posts;

    @OneToMany(mappedBy = "whom")
    @EqualsAndHashCode.Exclude
    private List<Subscription> subscriptions;

}
