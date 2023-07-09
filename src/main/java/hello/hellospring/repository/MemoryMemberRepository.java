package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 구현 클래스
public class MemoryMemberRepository implements MemberRepository {
    // static 자료형은 해당 클래스의 모든 객체가 공유하므로 한개만 존재함!!!!!!
    private static Map<Long, Member> store = new HashMap<>();// ****db 역할****
    private static long sequence = 0L;//0,1,2.. key값을 설정해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence);//id는 여기서 지정해줌
        store.put(member.getId(), member);//HashMap에 key/value 쌍 추가
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//null이어도 반환할 수 있게 optional 객체로 감쌈
    }

    @Override
    public Optional<Member> findByName(String name) {
        // values() : 열거된 모든 원소를 배열에 담아 순서대로 리턴
        // stream() : 컬렉션, 배열안에 있는 요소들을 하나씩 참조하며 반복적인 처리
        // filter() : 원하는 조건의 데이터를 뽑는데
        // findAny(): 가장 먼저 조건에 부합하는 데이터를 반환함
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//value(member)들을 리스트 형태로 만들어서 반환
    }

    public void clearStore(){
        store.clear();//store를 비움
    }
}
