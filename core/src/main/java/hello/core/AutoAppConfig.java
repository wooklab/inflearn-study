package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 자동 등록을 막기 위해
)
public class AutoAppConfig {

    // 수동 빈이 자동 빈을 오버라이딩 해버린다
    // Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing
    /*@Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
    // 테스트 케이스가 아닌 스프링 부트로 실행을 할 경우, 수동 빈 등록과 자동 빈 등록이 충돌나면 오류 발생을 기본으로 한다.
    //  - application.properties에 spring.main.allow-bean-definition-overriding=true 로 한다면 오류가 나지 않도록 설정이 가능은 하다.
}
