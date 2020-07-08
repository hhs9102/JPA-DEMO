package me.ham.identity.embeddable.id;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class GrandChildId implements Serializable {

    private ChildId child;

    @Column(name="grand_child_id")
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
