package ccampo133.blog.controller;

import ccampo133.blog.domain.Post;
import ccampo133.blog.exception.PostNotFoundException;
import ccampo133.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody final Post post, final Principal principal) {
        Post newPost = postService.createPost(post, principal.getName());
        return new ResponseEntity<Post>(newPost, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post getSinglePost(@PathVariable("id") final long id) throws PostNotFoundException {
        return postService.getPostById(id);
    }

    // UPDATE
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> updatePost(@PathVariable("id") final long id, @RequestBody final Post post)
            throws PostNotFoundException {
        postService.updatePost(post, id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable("id") final long id) throws PostNotFoundException {
        postService.deletePostById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
