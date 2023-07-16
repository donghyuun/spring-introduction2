package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired //생성자가 하나인 경우 생략가능
    public SpringConfig(MemberRepository memberRepository){//스프링 데이터 Jpa 가 만들어놓은 구현체가 등록됨
        this.memberRepository = memberRepository;
    }

    @Bean//스프링 빈에 등록
    public MemberService memberService(){
        return new MemberService(memberRepository);//스프링 빈에 등록되어 여기서 injection 받은 memberRepository를 넣어줌
    }

    //@Bean//스프링 빈에 등록
    //public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    //}
}
