package me.ham.identity.embeddable;

import lombok.Data;
import me.ham.identity.embeddable.id.GrandChildId;

import javax.persistence.*;

@Entity(name="grand_child_embeddable")
@Data
public class GrandChild extends Person {

    @EmbeddedId
    private GrandChildId id;

    @MapsId("child")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "parent_id")
            ,@JoinColumn(name = "child_id")
    })
    private Child child;
}
