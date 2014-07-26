package ccampo133.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


// Lots of JPA magic in this class. The database schema will be generated
// based on the structure (getters, setters, id annotation) of this POJO.

@Entity
@SuppressWarnings("UnusedDeclaration")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    private String author;
    private String body;
    private String title;
    private Date date;

    // Primary key - auto incremented long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
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

    public Date getDate() {
        return date;
    }

    @JsonProperty(required = false)
    public void setDate(final Date date) {
        this.date = date;
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
}
