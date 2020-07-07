package me.ham.identity.id;

import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
public class ChildId implements Serializable {

    private Long parent;
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildId)) return false;
        ChildId childId = (ChildId) o;
        return Objects.equals(parent, childId.parent) &&
                Objects.equals(id, childId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, id);
    }
}
