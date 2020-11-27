package ru.itis.hateoas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.hateoas.interfaces.WithAuthorInt;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PostLike extends AbstractEntity implements WithAuthorInt {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_user_id")
    private BlogUser author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public void set(Long id) {

        Long likesCount = post.getLikesCount();
        List<PostLike> likes = post.getLikes();

        boolean likeIsExist = false;

        for (PostLike like : likes) {
            if (like.author.equals(author)) {
                post.setLikesCount(likesCount - 1);
                likeIsExist = true;
            }
        }

        if(!likeIsExist) {
            post.setLikesCount(likesCount + 1);
        }
    }

}