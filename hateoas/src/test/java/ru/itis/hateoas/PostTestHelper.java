package ru.itis.hateoas;

import ru.itis.hateoas.model.Post;

public class PostTestHelper {

    public static Post getPost(boolean userIsConfirmed) {
        return Post.builder()
                .body("Test body")
                .title("Test title")
                .author(UserTestHelper.getUser(userIsConfirmed))
                .build();
    }

}
