package me.ham.identity.id;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class GrandChildId implements Serializable {

    private ChildId child;
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrandChildId)) return false;
        GrandChildId that = (GrandChildId) o;
        return Objects.equals(getChild(), that.getChild()) &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChild(), getId());
    }
}
