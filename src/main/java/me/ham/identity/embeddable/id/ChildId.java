package me.ham.identity.embeddable.id;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class ChildId implements Serializable {

    private Long parentId;

    @Column(name = "child_id")
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildId)) return false;
        ChildId childId = (ChildId) o;
        return Objects.equals(parentId, childId.parentId) &&
                Objects.equals(id, childId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, id);
    }
}
