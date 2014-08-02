package ccampo133.blog.service;

import ccampo133.blog.domain.Post;
import ccampo133.blog.exception.PostNotFoundException;
import ccampo133.blog.repository.PostRepository;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class PostServiceTest {

    @Mocked
    @Injectable
    private PostRepository postRepository;

    @Tested
    private PostService postService;

    @Test
    public void createPostSetsDateAndAuthorAndSaves(@Mocked final Post post, @Mocked final Date date) {
        final String username = "TEST_USER";

        new NonStrictExpectations() {{
            new Date();
            result = null;
        }};

        new Expectations() {{
            post.setDate(new Date());
            post.setAuthor(username);
            postRepository.save(post);
            result = post;
        }};

        Post result = postService.createPost(post, username);
        assertEquals("Result does not match expected.", post, result);
    }

    @Test
    public void getAllPostsReturnsList(@Mocked final List<Post> posts) {
        new Expectations() {{
            postRepository.findAll();
            result = posts;
        }};

        List<Post> result = postService.getAllPosts();
        assertEquals("Result does not match expected.", posts, result);
    }

    @Test(expected = PostNotFoundException.class)
    public void getPostByIdThrowsPostNotFoundExceptionWhenNull() throws PostNotFoundException {
        final long id = 1337;
        new Expectations() {{
            postRepository.findOne(id);
            result = null;
        }};

        postService.getPostById(id);
    }

    @Test
    public void getPostByIdIsSuccessful(@Mocked final Post post) throws PostNotFoundException {
        final long id = 1337;
        new Expectations() {{
            postRepository.findOne(id);
            result = post;
        }};

        Post result = postService.getPostById(id);
        assertEquals("Result does not match expected.", post, result);
    }

    @Test(expected = PostNotFoundException.class)
    public void updatePostByIdThrowsExceptionWhenNull(@Mocked final Post post) throws PostNotFoundException {
        final long id = 1337;
        new Expectations() {{
            postRepository.findOne(id);
            result = null;
        }};

        postService.updatePostById(id, post);
    }

    @Test
    public void updatePostByIdSetsBodyAndTitleWhenNotNullAndSaves(@Mocked final Post post)
            throws PostNotFoundException {
        new NonStrictExpectations() {{
            postRepository.findOne(anyLong);
            result = post;

            postRepository.save(post);

            post.getBody();
            result = anyString;

            post.getTitle();
            result = anyString;
        }};

        postService.updatePostById(123, post);

        new VerificationsInOrder() {{
            post.setBody(anyString);
            times = 1;

            post.setTitle(anyString);
            times = 1;

            postRepository.save(post);
            times = 1;
        }};
    }

    @Test
    public void updatePostByIdDoesNotSetBodyAndTitleWhenNullAndSaves(@Mocked final Post post)
            throws PostNotFoundException {
        new NonStrictExpectations() {{
            postRepository.findOne(anyLong);
            result = post;

            postRepository.save(post);

            post.getBody();
            result = null;

            post.getTitle();
            result = null;
        }};

        postService.updatePostById(123, post);

        new VerificationsInOrder() {{
            post.setBody(anyString);
            times = 0;

            post.setTitle(anyString);
            times = 0;

            postRepository.save(post);
            times = 1;
        }};
    }

    @Test(expected = PostNotFoundException.class)
    public void deletePostByIdThrowsPostNotFoundException(@Mocked final EmptyResultDataAccessException ex)
            throws PostNotFoundException {
        final long id = 1;
        new Expectations() {{
            postRepository.delete(id);
            result = ex;
        }};

        postService.deletePostById(id);
    }

    @Test
    public void deletePostByIdIsSuccessful() throws PostNotFoundException {
        final long id = 2;
        new Expectations() {{
            postRepository.delete(id);
        }};

        postService.deletePostById(id);
    }
}
