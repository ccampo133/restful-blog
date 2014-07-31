package ccampo133.blog.controller;

import ccampo133.blog.domain.Post;
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

    /*
    @Test
    public void createPostIsSuccessful(@Mocked final Post post, @Mocked final Principal principal) throws Exception {
        new Expectations() {{
            postService.createPost(post, principal.getName());
            result = post;
        }};

        ResponseEntity<Post> result = postController.createPost(post, principal);

        assertEquals("Response should be 201 Created.", HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Response body mismatch.", post, result.getBody());
    }*/

    @Test
    public void getAllPostsIsSuccessful(@Mocked final List<Post> allPosts) throws Exception {
        new Expectations() {{
            postService.getAllPosts();
            result = allPosts;
        }};

        List<PostResource> result = postController.getAllPosts();

        assertEquals("Returned results do not match expected.", allPosts, result);
    }

    @Test
    public void getSinglePostIsSuccessful(@Mocked final Post post) throws Exception {
        final long id = 1234;
        new Expectations() {{
            postService.getPostById(id);
            result = post;
        }};

        PostResource result = postController.getSinglePost(id);

        assertEquals("Result does not match expected.", post, result);
    }

    // TODO: handle not found exception case

    /*
    @Test
    public void updatePostIsSuccessfulAndReturnsStatusNoContent(@Mocked final Post post,
            @Mocked final Principal principal) throws Exception {
        final long id = 1234;
        new Expectations() {{
            postService.updatePost(post, id, principal.getName());
        }};

        ResponseEntity<Void> result = postController.updatePost(id, post, principal);

        assertEquals("Status is not 204 No Content.", HttpStatus.NO_CONTENT, result.getStatusCode());
    }*/

    @Test
    public void deletePostIsSuccessfulAndReturnsStatusOK(@Mocked final Post post) throws Exception {
        final long id = 1234;
        new Expectations() {{
            postService.deletePostById(id);
        }};

        ResponseEntity<Void> result = postController.deletePost(id);

        assertEquals("Status is not 200 OK.", HttpStatus.OK, result.getStatusCode());
    }
}
