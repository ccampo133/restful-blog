package ccampo133.blog.resource.assembler;

import ccampo133.blog.controller.PostController;
import ccampo133.blog.domain.Post;
import ccampo133.blog.resource.PostResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PostResourceAssembler extends ResourceAssemblerSupport<Post, PostResource> {

    public PostResourceAssembler() {
        super(PostController.class, PostResource.class);
    }

    @Override
    public PostResource toResource(final Post post) {
        PostResource resource = createResourceWithId(post.getId(), post);

        // Map the domain object to the resource representation.
        resource.setAuthor(post.getAuthor());
        resource.setBody(post.getBody());
        resource.setDate(post.getDate());
        resource.setTitle(post.getTitle());

        return resource;
    }
}
