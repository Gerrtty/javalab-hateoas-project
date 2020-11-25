//package ru.itis.hateoas.processor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.RepresentationModelProcessor;
//import org.springframework.stereotype.Component;
//import ru.itis.hateoas.model.Post;
//
//@Component
//public class PostsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Post>> {
//
//    private final RepositoryEntityLinks repositoryEntityLinks;
//
//    @Autowired
//    public PostsRepresentationProcessor(RepositoryEntityLinks repositoryEntityLinks) {
//        this.repositoryEntityLinks = repositoryEntityLinks;
//    }
//
//    @Override
//    public EntityModel<Post> process(EntityModel<Post> model) {
//        return null;
//    }
//
//}
