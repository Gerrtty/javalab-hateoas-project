package ru.itis.hateoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoas.model.Comment;
import ru.itis.hateoas.repository.CommentsRepository;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public Comment publish(Comment comment) throws IllegalAccessException {

        if (comment != null && comment.getAuthor() != null && comment.getAuthor().isConfirmed()) {
            return commentsRepository.save(comment);
        }

        else throw new IllegalAccessException("User not confirmed");
    }
}
