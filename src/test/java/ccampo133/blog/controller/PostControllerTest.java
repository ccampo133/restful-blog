package ccampo133.blog.controller;

import ccampo133.blog.domain.Post;
import ccampo133.blog.exception.PostNotFoundException;
import ccampo133.blog.resource.PostResource;
import ccampo133.blog.resource.assembler.PostResourceAssembler;
import ccampo133.blog.service.PostService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

import static org.junit.Assert.assertEquals;

// TODO: redo these tests to support HATEOAS. Perhaps use Mockito for simplicity?
@RunWith(JMockit.class)
public class PostControllerTest {

    @Mocked
    @Injectable
    private PostResourceAssembler postResourceAssembler;

    @Mocked
    @Injectable
    private PostService postService;

    @Tested
    private PostController postController;

    @Test
    public void createPostIsSuccessful(@Mocked final Post post, @Mocked final Principal principal,
            @Mocked final PostResource resource) throws Exception {
        new Expectations() {{
            postService.createPost(post, principal.getName());
            result = post;

            postResourceAssembler.toResource(post);
            result = resource;
        }};

        ResponseEntity<PostResource> result = postController.createPost(post, principal);

        assertEquals("Response status should be 201 Created.", HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Response body mismatch.", resource, result.getBody());
    }

    @Test
    public void getAllPostsIsSuccessful(@Mocked final List<Post> posts, @Mocked final List<PostResource> resources) {
        new Expectations() {{
            postService.getAllPosts();
            result = posts;

            postResourceAssembler.toResources(posts);
            result = resources;
        }};

        List<PostResource> result = postController.getAllPosts();
        assertEquals("Result does not match expected.", resources, result);
    }

    @Test
    public void getPostByIdIsSuccessful(@Mocked final Post post, @Mocked final PostResource resource)
            throws PostNotFoundException {
        final long id = 1234;
        new Expectations() {{
            postService.getPostById(id);
            result = post;

            postResourceAssembler.toResource(post);
            result = resource;
        }};

        PostResource result = postController.getPostById(id);
        assertEquals("Result does not match expected.", resource, result);
    }

    @Test
    public void updatePostByIdIsSuccessful(@Mocked final Post post) throws PostNotFoundException {
        final long id = 1234;
        new Expectations() {{
            postService.updatePostById(id, post);
        }};

        ResponseEntity<Void> result = postController.updatePostById(id, post);
        assertEquals("Response status should be 204 No Content", HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void deletePostByIdIsSuccessful() throws PostNotFoundException {
        final long id = 1234;
        new Expectations() {{
            postService.deletePostById(id);
        }};

        ResponseEntity<Void> result = postController.deletePostById(id);
        assertEquals("Response status should be 200 OK", HttpStatus.OK, result.getStatusCode());
    }
}
