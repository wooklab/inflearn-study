package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	/*
	 * 스프링 부트는 웹 라이브러리가 없으면 우리가 지금까지 학습한
	 * AnnotationConfigApplicationContext 을 기반으로 애플리케이션을 구동한다.
	 * 웹 라이브러리가 추가 되면 웹과 관련된 추가 설정과 환경들이 필요하므로
	 * AnnotationConfigServletWebServerApplicationContext 기반으로 구동한다.
 	 */

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
