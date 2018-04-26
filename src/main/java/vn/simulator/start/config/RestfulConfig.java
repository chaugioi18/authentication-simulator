package vn.simulator.start.config;

public class RestfulConfig {

    private String host;
    private Integer port;

    public String getHost() {
        return host;
    }

    public RestfulConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public RestfulConfig setPort(Integer port) {
        this.port = port;
        return this;
    }
}
