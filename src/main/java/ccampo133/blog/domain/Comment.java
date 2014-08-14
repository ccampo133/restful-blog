package ccampo133.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@SuppressWarnings("UnusedDeclaration")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    private Date date;
    private String author;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Post getPost() {
        return post;
    }

    @JsonProperty(required = false)
    public void setPost(final Post post) {
        this.post = post;
    }

    public long getId() {
        return id;
    }

    @JsonProperty(required = false)
    public void setId(final long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    @JsonProperty(required = false)
    public void setDate(final Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    @JsonProperty(required = false)
    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
}
