package ccampo133.blog.controller;

import ccampo133.blog.domain.Post;
import ccampo133.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chriscampo on 7/21/14.
 */
@RestController
public class PostController {

    private static final String POSTS_URI = "/api/posts";

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = POSTS_URI, method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @RequestMapping(value = POSTS_URI, method = RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody final Post post) {
        Post newPost = postService.createPost(post);
        return new ResponseEntity<Post>(newPost, HttpStatus.CREATED);
    }

}
