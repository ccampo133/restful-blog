package ccampo133.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by chriscampo on 7/28/14.
 */
@Entity
@SuppressWarnings("UnusedDeclaration")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    private Date date;
    private String author;
    private String body;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }
}
