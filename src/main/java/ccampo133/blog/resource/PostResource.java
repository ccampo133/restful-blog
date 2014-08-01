package ccampo133.blog.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

@SuppressWarnings("UnusedDeclaration")
public class PostResource extends ResourceSupport {

    private String author;
    private String body;
    private String title;
    private Date date;
    private long postId;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    // We have to use the variable name 'postId' to avoid stepping on the feet of the HATEOAS 'ResourceSupport' class,
    // which already defines this property (getter) to return a self-referencing link.
    @JsonProperty(value = "id")
    public long getPostId() {
        return postId;
    }

    public void setPostId(final long postId) {
        this.postId = postId;
    }
}
