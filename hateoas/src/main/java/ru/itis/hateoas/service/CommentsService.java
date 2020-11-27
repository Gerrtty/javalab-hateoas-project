package ru.itis.hateoas.service;

import ru.itis.hateoas.model.Comment;

public interface CommentsService {

    Comment publish(Comment comment) throws IllegalAccessException;

}
