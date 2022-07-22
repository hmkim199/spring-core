package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

//    @Test
//    void fieldInjectionTest() {
//        // 의존성 주입 - 필드 인젝션 하면 수정할 수 없으므로 순수 자바 쓰는 테스트 만들려면 이런 식으로 setter를 다시 만들어줘야함....
//        // 그럴 거면 수정자 주입을 쓰겠지~! 이도 저도 아니라서 쓰지 말자.
//        OrderServiceImpl orderService = new OrderServiceImpl();
//
////        orderService.setMemberRepository(new MemoryMemberRepository());
////        orderService.setDiscountPolicy(new FixDiscountPolicy());
//
//        orderService.createOrder(1L, "itemA", 10000);
//    }
}
