package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {  // 여기서 돌리면 전체 테스트 코드를 실행
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach  // 콜백 메서드로 지정
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save()  {
        Member member = new Member();
        member.setName("spring");
        // 줄 바로 내려가기 : ctrl + shift + enter
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        // 테스트 작성방법 1
//        System.out.println("result = " + (result == member));
        // 테스트 작성방법 2
//        Assertions.assertEquals(result, member);

        // 테스트 작성방법 3
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // rename : shift + f6
        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
