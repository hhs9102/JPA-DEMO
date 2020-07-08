package me.ham.identity.idclass;

import lombok.Data;
import me.ham.identity.idclass.id.GrandChildId;

import javax.persistence.*;

@Entity
@Data
@IdClass(GrandChildId.class)
public class GrandChild extends Person{
    @Id
    @Column(name = "grand_child_id")
    private Long id;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "parent_id")
            ,@JoinColumn(name = "child_id")
    })
    private Child child;
}
