package ccampo133.blog.controller;

import ccampo133.blog.domain.Post;
import ccampo133.blog.exception.PostNotFoundException;
import ccampo133.blog.resource.PostResource;
import ccampo133.blog.resource.assembler.PostResourceAssembler;
import ccampo133.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    private final PostService postService;
    private final PostResourceAssembler postResourceAssembler;

    @Autowired
    public PostController(final PostService postService, final PostResourceAssembler postResourceAssembler) {
        this.postService = postService;
        this.postResourceAssembler = postResourceAssembler;
    }

    // CREATE
    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PostResource> createPost(@RequestBody final Post post, final Principal principal) {
        Post newPost = postService.createPost(post, principal.getName());
        PostResource newPostResource = postResourceAssembler.toResource(newPost);
        return new ResponseEntity<>(newPostResource, HttpStatus.CREATED);
    }

    // READ
    @RequestMapping(method = RequestMethod.GET)
    public PagedResources<PostResource> getAllPosts(final Pageable pageable,
            final PagedResourcesAssembler<Post> assembler) {
        Page<Post> posts = postService.getAllPosts(pageable);
        return assembler.toResource(posts, postResourceAssembler);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PostResource getPostById(@PathVariable("id") final long id) throws PostNotFoundException {
        Post post = postService.getPostById(id);
        return postResourceAssembler.toResource(post);
    }

    // UPDATE
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> updatePostById(@PathVariable("id") final long id, @RequestBody final Post post)
            throws PostNotFoundException {
        postService.updatePostById(id, post);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // DELETE
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePostById(@PathVariable("id") final long id) throws PostNotFoundException {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
