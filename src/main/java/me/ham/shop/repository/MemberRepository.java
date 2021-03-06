package me.ham.shop.repository;

import lombok.extern.slf4j.Slf4j;
import me.ham.shop.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
@Transactional
public class MemberRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Member getMember(long id){
        Member member = entityManager.find(Member.class, id);
        log.info(member.toString());
        return member;
    }

    public List getMembers() {
        return null;
    }
    public void insertMember(Member member) {
        entityManager.persist(member);
        log.info(member.toString());
    }

    public void updateMember(Member member) {
        Member dbMember = entityManager.find(Member.class, member);
        dbMember.setName(member.getName());
        dbMember.setCity(member.getCity());
        entityManager.persist(dbMember);
        log.info(dbMember.toString());
    }
}
