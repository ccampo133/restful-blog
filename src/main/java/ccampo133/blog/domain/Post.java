package ccampo133.blog.domain;

import java.util.Date;

/**
 * Created by chriscampo on 7/21/14.
 */
public class Post {

    private String author;
    private String body;
    private Date date;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
