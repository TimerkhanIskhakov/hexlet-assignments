package exercise.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.GenerationType;
import java.util.EnumSet;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob
    private String body;

    private PostState state = PostState.CREATED;

    // BEGIN
    public boolean publish() {
        if (state != PostState.CREATED) {
            return false;
        }

        state = PostState.PUBLISHED;
        return true;
    }

    public boolean archive() {
        if (!EnumSet.of(PostState.CREATED, PostState.PUBLISHED).contains(state)) {
            return false;
        }

        state = PostState.ARCHIVED;
        return true;
    }
    // END
}
