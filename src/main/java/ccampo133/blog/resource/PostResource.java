package ccampo133.blog.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

public class PostResource extends ResourceSupport {

    private String author;
    private String body;
    private String title;
    private Date date;

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
}
