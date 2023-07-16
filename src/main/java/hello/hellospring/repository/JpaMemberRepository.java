package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // Jpa라이브러리 받으면 springboot가 자동으로 만들어줌,
    // data connection 등등 싹 다 짬뽕해서 만들어줘서 injection만 받으면 됌
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);//Jpa 가 insert 쿼리 만들어서 다 집어넣고, id까지 멤버에 setId 하는 등 모든 걸 다 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //(조회할 타입, 식별자pk)// Jpa가 자동으로 select 쿼리 만들어서 ~~
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //pk 기반이 아닌 것들은 아래의 Jpql 이라는 걸 작성해줘야 함
        //where ~ : 조건이 ~ 인것
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                        .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //Member entity 자체, 즉 객체 자체를 select 함
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        return result;
    }
}
