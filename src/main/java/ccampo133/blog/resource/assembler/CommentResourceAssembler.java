package ccampo133.blog.resource.assembler;

import ccampo133.blog.controller.CommentController;
import ccampo133.blog.controller.PostController;
import ccampo133.blog.domain.Comment;
import ccampo133.blog.resource.CommentResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CommentResourceAssembler extends ResourceAssemblerSupport<Comment, CommentResource> {

    public CommentResourceAssembler() {
        super(CommentController.class, CommentResource.class);
    }

    @Override
    public CommentResource toResource(final Comment comment) {
        CommentResource resource = createResourceWithId(comment.getId(), comment, comment.getPost().getId());

        // Define links
        resource.add(linkTo(PostController.class).slash(comment.getPost().getId()).withRel("post"));

        return resource;
    }

    @Override
    protected CommentResource instantiateResource(final Comment comment) {
        CommentResource resource = new CommentResource();
        resource.setAuthor(comment.getAuthor());
        resource.setBody(comment.getContent());
        resource.setDate(comment.getDate());
        resource.setCommentId(comment.getId());
        return resource;
    }
}
