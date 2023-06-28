package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);//회원 db에 저장
    Optional<Member> findById(Long id);//db에서 id로 회원 찾음
    Optional<Member> findByName(String name);//db에서 id로 회원 찾음
    List<Member> findAll();//db에 있는 모든 회원 가져오기
}
