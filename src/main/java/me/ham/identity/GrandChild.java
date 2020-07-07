package me.ham.identity;

import lombok.Getter;
import me.ham.identity.id.GrandChildId;

import javax.persistence.*;

@Entity
@Getter
@IdClass(GrandChildId.class)
public class GrandChild {
    @Id
    @Column(name = "grand_child_id")
    @GeneratedValue
    private Long id;


    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "parent_id")
            ,@JoinColumn(name = "child_id")
    })
    private Child child;
}
