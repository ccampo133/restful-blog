package ccampo133.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by chriscampo on 7/21/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    private String author;
    private String body;
    private String title;
    private long id;
    private Date date;

    public Long getId() {
        return id;
    }

    @JsonProperty(required = false)
    public void setId(final long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    @JsonProperty(required = false)
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

    @JsonProperty(required = false)
    public void setDate(final Date date) {
        this.date = date;
    }
}
