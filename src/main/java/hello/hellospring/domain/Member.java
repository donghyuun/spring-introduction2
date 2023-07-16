package hello.hellospring.domain;
//JPA는 ORM이라는 기술이다. 'O'bject와 'R'elational DB의 table을 'M'apping 한다는 뜻

import jakarta.persistence.*;

@Entity //Jpa가 관리하는 Entity라는 뜻
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //@Id는 pk라는 뜻 //IDENTITY: DB가 자동으로 생성해준다는 뜻
    private Long id;//데이터 구분용으로 시스템이 저장하는 id

    @Column(name = "name") //이 어노테이션을 가지고 db와 매핑하는것
    private String name;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
