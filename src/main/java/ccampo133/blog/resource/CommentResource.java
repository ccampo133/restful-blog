package ccampo133.blog.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

@SuppressWarnings("UnusedDeclaration")
public class CommentResource extends ResourceSupport {

    public String author;
    public String body;
    public Date date;
    public long id;

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

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

    @JsonProperty(value = "id")
    public long getCommentId() {
        return id;
    }

    public void setCommentId(final long id) {
        this.id = id;
    }
}
