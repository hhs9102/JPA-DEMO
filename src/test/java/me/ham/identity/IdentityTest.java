package me.ham.identity;

import me.ham.identity.id.ChildId;
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
        Parent parent = new Parent();
        parent.setName("함넘버원");
        parent.setAge(121);

        entityManager.persist(parent);
        Parent parent1 = entityManager.find(Parent.class, parent.getId());
        System.out.println(parent1);
        Assert.assertEquals(parent.getName(), parent1.getName());

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
    }
}
