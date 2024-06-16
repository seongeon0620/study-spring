package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
// @Transactional : @Test  어노테이션이 붙어있는 테스트를 수행하고나서 db에 올리지않고 롤백함
class MemberServiceIntegrationTest {

    @Autowired
    MemberService2 memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Commit
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello1112");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        
        Member member2 = new Member();
        member2.setName("spring1");

        // when
        memberService.join(member1);


        // try-catch를 대체할 문법 제공!
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        // then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}