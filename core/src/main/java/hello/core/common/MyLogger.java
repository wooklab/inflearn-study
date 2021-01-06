package hello.core.common;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 적용 대상이 클래스이기 때문에 TARGET_CLASS
 * 인터페이스라면 INTERFACES
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.printf("[%s][%s]%s%n", uuid, requestURL, message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.printf("[%s] request scope bean create: %s%n", uuid, this);
    }

    @PreDestroy
    public void close() {
        System.out.printf("[%s] request scope bean create: %s%n", uuid, this);
    }
}
