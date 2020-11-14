package ru.itis.hateoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.model.BlogUser;
import ru.itis.hateoas.model.Post;
import ru.itis.hateoas.repository.PostsRepository;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    @Autowired
    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public Post publish(Post post) {
        return postsRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postsRepository.delete(postsRepository.getOne(id));
    }

    @Override
    public List<Post> getAllByUser(BlogUser blogUser) {
        return blogUser.getPosts();
    }

}
