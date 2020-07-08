package me.ham.identity.embeddable;

import lombok.Getter;
import lombok.Setter;
import me.ham.identity.embeddable.id.ChildId;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "child_embeddable")
@Getter
@Setter
public class Child  extends Person {

    /* 복합키에는 generatedValue를 이용할 수 없다. */
    @EmbeddedId
    private ChildId childId;

    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Child)) return false;
        if (!super.equals(o)) return false;
        Child child = (Child) o;
        return Objects.equals(getChildId(), child.getChildId()) &&
                Objects.equals(getParent(), child.getParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getChildId(), getParent());
    }
}
