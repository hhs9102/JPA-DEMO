package me.ham.identity;

import lombok.Getter;
import lombok.Setter;
import me.ham.identity.id.ChildId;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.cglib.core.DefaultGeneratorStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@IdClass(ChildId.class)
public class Child  extends Person{

    @Id
    @Column(name = "CHILD_ID")
    /* 복합키에는 generatedValue를 이용할 수 없다. */
    private Long id;


    @Id
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Child)) return false;
        Child child = (Child) o;
        return Objects.equals(id, child.id) &&
                Objects.equals(parent, child.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parent);
    }
}
