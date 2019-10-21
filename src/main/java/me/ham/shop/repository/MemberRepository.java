package me.ham.shop.repository;

import lombok.extern.slf4j.Slf4j;
import me.ham.shop.entity.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

}
