package api.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "COMMENTS")
public class Comment implements Serializable {
    private Long id;
    private String text;

    private Cookie cookie;

    private User author;

    private Set<User> thumbsUp = new HashSet<>();
    private Set<User> thumbsDown = new HashSet<>();

    public Comment() {
        //it is needed for hibernate, nothing more to be added
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "thumbs_up",
            joinColumns = {@JoinColumn(name = "comment_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    public Set<User> getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Set<User> thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "thumbs_down",
            joinColumns = {@JoinColumn(name = "comment_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    public Set<User> getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(Set<User> thumbsDown) {
        this.thumbsDown = thumbsDown;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getText(), comment.getText()) &&
                Objects.equals(getCookie(), comment.getCookie()) &&
                Objects.equals(getAuthor(), comment.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getCookie(), getAuthor());
    }
}
