package ccampo133.blog.controller;

import ccampo133.blog.domain.Post;
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

@RunWith(JMockit.class)
public class PostControllerTest {

    @Mocked
    @Injectable
    private PostService postService;

    @Tested
    private PostController postController;

    @Test
    public void createPostIsSuccessful(@Mocked final Post post, @Mocked final Principal principal) throws Exception {
        new Expectations() {{
            postService.createPost(post, principal.getName());
            times = 1;
            result = post;
        }};

        ResponseEntity<Post> result = postController.createPost(post, principal);

        assertEquals("Response should be 201 Created.", HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Response body mismatch.", post, result.getBody());
    }

    @Test
    public void getAllPostsIsSuccessful(@Mocked final List<Post> allPosts) throws Exception {
        new Expectations(){{
            postService.getAllPosts();
            times = 1;
            result = allPosts;
        }};

        List<Post> result = postController.getAllPosts();

        assertEquals("Returned results do not match expected.", allPosts, result);
    }

    @Test
    public void getSinglePostIsSuccessful(@Mocked final Post post) throws Exception {
        final long id = 1234;
        new Expectations(){{
            postService.getPostById(id);
            times = 1;
            result = post;
        }};

        Post result = postController.getSinglePost(id);

        assertEquals("Result does not match expected.", post, result);
    }

    @Test
    public void updatePostIsSuccessfulAndReturnsStatusNoContent(@Mocked final Post post) throws Exception {
        final long id = 1234;
        new Expectations(){{
            postService.updatePost(id, post);
            times = 1;
        }};

        ResponseEntity<Void> result = postController.updatePost(id, post);

        assertEquals("Status is not 204 No Content.", HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void deletePostIsSuccessfulAndReturnsStatusOK(@Mocked final Post post) throws Exception {
        final long id = 1234;
        new Expectations(){{
            postService.deletePostById(id);
            times = 1;
        }};

        ResponseEntity<Void> result = postController.deletePost(id);

        assertEquals("Status is not 200 OK.", HttpStatus.OK, result.getStatusCode());
    }
}
