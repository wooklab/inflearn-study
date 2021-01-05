package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class BeanLifeCycleTest {

    @Test
    void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    static class LifeCycleConfig {

        /**
         * destroyMethod 의 기본값 => (inferred)
         * @Bean 등록시 종료메서드(`close`, `shutdown`)를 자동으로 호출해준다.
         * - 만약 호출하고 싶지 않다면 `destroyMethod = ""` 처리하면 된다.
         * @see AbstractBeanDefinition#INFER_METHOD
         */
        @Bean(initMethod =  "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
