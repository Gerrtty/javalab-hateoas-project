package ru.itis.hateoas.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "whom")
    private List<Subscription> subscriptions;

}
