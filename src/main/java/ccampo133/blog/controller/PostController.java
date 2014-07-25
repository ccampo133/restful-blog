package ccampo133.blog.controller;

import ccampo133.blog.domain.Post;
import ccampo133.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by chriscampo on 7/21/14.
 */
@RestController
public class PostController {

    private static final String ALL_POSTS = "/api/posts";
    private static final String SINGLE_POST = "/api/posts/{id}";

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // CREATE
    @RequestMapping(value = ALL_POSTS, method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody final Post post, final Principal principal) {
        Post newPost = postService.createPost(post, principal.getName());
        return new ResponseEntity<Post>(newPost, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(value = ALL_POSTS, method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(value = SINGLE_POST, method = RequestMethod.GET)
    public Post getSinglePost(@PathVariable("id") final long id) {
        return postService.getPostById(id);
    }

    // UPDATE
    @RequestMapping(value = SINGLE_POST, method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePost(@PathVariable("id") final long id, @RequestBody final Post post) {
        postService.updatePost(id, post);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // DELETE
    @RequestMapping(value = SINGLE_POST, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable("id") final long id) {
        postService.deletePostById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
