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
        return resource;
    }

    @Override protected PostResource instantiateResource(final Post post) {
        // Map the domain object to the resource representation.
        PostResource resource = new PostResource();
        resource.setAuthor(post.getAuthor());
        resource.setBody(post.getBody());
        resource.setDate(post.getDate());
        resource.setTitle(post.getTitle());
        resource.setPostId(post.getId());
        return resource;
    }
}
