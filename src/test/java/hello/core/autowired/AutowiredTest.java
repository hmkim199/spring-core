package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import javax.swing.text.html.Option;
import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // 자동 주입 대상을 option으로 처리하는 방법 세가지!
        
        // 1. 얘는 아예 호출 안 됨. required false라서
        @Autowired(required = false)
        public void SetNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 2. 얘는 호출되고 null이 들어감.
        @Autowired
        public void SetNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
        
        // 3. 얘는 optional로 감싸짐
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
