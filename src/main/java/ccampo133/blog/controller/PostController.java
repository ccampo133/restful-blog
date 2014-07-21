package ccampo133.blog.controller;

import ccampo133.blog.domain.Post;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chriscampo on 7/21/14.
 */

@RestController
public class PostController {

    @RequestMapping("/api/post")
    public List<Post> getAllPosts() {
        return Arrays.asList(new Post(), new Post(), new Post());
    }

}
