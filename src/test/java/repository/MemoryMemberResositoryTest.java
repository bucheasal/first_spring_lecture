package repository;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository =new MemoryMemberRepository();

    @AfterEach //@끝날 때 마다 실행하는 코드
    public void afterEach() {
        repository.clearStore();
    }
    // test는 순서 의존 관계가 없어야한다. 한 번 테스트마다 코드 지워주는 코드 필요하다.
    @Test
    public void save(){
        Member member = new Member();
        member.setName("sring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional 반환 시 get() 으로 받아야한다
        Assertions.assertEquals(member, result);

        assertThat(member).isEqualTo(result); //방법 2
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result =repository.findByName("spring1").get(); //get으로 받으면 optional 한 번 까서 꺼낼 수 잇음

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

        //class test 돌려보면 순서가 findAll 먼저 돌아감. 그리고 findByName 실행 시 미리 저장된 객체 반환되는 오류.. test 하나 끝나면 데이터 초기화하는 코드 삽입 필요
    }
}
