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
public class Post extends AbstractEntity {

    private String title;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_user_id")
    @EqualsAndHashCode.Exclude
    private BlogUser author;

    @OneToMany(mappedBy = "post")
    @EqualsAndHashCode.Exclude
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    @EqualsAndHashCode.Exclude
    private List<PostLike> likes;

    private Long likesCount;
}
