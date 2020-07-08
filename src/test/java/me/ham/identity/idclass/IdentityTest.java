package me.ham.identity.idclass;

import me.ham.identity.idclass.Child;
import me.ham.identity.idclass.GrandChild;
import me.ham.identity.idclass.Parent;
import me.ham.identity.idclass.id.ChildId;
import me.ham.identity.idclass.id.GrandChildId;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
@Commit
@Transactional(rollbackOn = IllegalArgumentException.class)
public class IdentityTest {
    @Autowired
    EntityManager entityManager;

    @Test
    public void insertFamilyTest(){
        /* Parent */
        Parent parent = new Parent();
        parent.setName("함넘버원");
        parent.setAge(121);

        entityManager.persist(parent);
        Parent parent1 = entityManager.find(Parent.class, parent.getId());
        System.out.println(parent1);
        Assert.assertEquals(parent.getName(), parent1.getName());

        /* Child */

        Child child = new Child();

        ChildId childId = new ChildId();
        childId.setParent(parent.getId());
        Long childRandomId = ThreadLocalRandom.current().nextLong(1, 9999);
        childId.setId(childRandomId);

        child.setName("함장남");
        child.setAge(17);
        child.setParent(parent);
        child.setId(childRandomId);

        entityManager.persist(child);
        Child child1 = entityManager.find(Child.class, childId);

        Assert.assertEquals(child.getName(), child1.getName());
        Assert.assertEquals(parent.getId(), child1.getParent().getId());

        /* GrandChild */

        GrandChild grandChild = new GrandChild();

        Long grandChildRandomId = ThreadLocalRandom.current().nextLong(1, 9999);
        GrandChildId grandChildId = new GrandChildId();
        grandChildId.setId(grandChildRandomId);
        grandChildId.setChild(childId);

        grandChild.setName("함아래");
        grandChild.setAge(3);
        grandChild.setId(grandChildRandomId);
        grandChild.setChild(child);

        entityManager.persist(grandChild);
        GrandChild grandChild1 = entityManager.find(GrandChild.class, grandChildId);

        Assert.assertEquals(grandChild.getId(), grandChild1.getId());
        Assert.assertEquals(grandChild1.getChild().getParent().getName(), grandChild.getChild().getParent().getName());
    }
}
