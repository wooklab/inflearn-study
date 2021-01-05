package hello.core.lifecycle;

public class NetworkClient {
    // implements InitializingBean, DisposableBean {   // Spring 에 의존적, 스프링 초창기 방법으로 지금은 거의 사용하지 않음

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    public void disconnect() {
        System.out.println("close: " + url);
    }

    public void init() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
