package vn.simulator.common.request;

import vn.simulator.common.helper.annotation.CanNullOrEmpty;
import vn.simulator.common.helper.annotation.HMAC;

public class LoginRequest {

    @HMAC
    private String username;

    @HMAC
    private char[] password;

    @HMAC
    private String integrationKey;

    @HMAC
    private Long unixTimestamp;

    @CanNullOrEmpty
    @HMAC
    private Boolean supportFido;

    @CanNullOrEmpty
    @HMAC
    private String ipAddress;

    @CanNullOrEmpty
    @HMAC
    private String userAgent;

    @CanNullOrEmpty
    @HMAC
    private String browserFp;

    private String hmac;

    public String getUsername() {
        return username;
    }

    public LoginRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public char[] getPassword() {
        return password;
    }

    public LoginRequest setPassword(char[] password) {
        this.password = password;
        return this;
    }

    public String getIntegrationKey() {
        return integrationKey;
    }

    public LoginRequest setIntegrationKey(String integrationKey) {
        this.integrationKey = integrationKey;
        return this;
    }

    public Long getUnixTimestamp() {
        return unixTimestamp;
    }

    public LoginRequest setUnixTimestamp(Long unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
        return this;
    }

    public Boolean getSupportFido() {
        return supportFido;
    }

    public LoginRequest setSupportFido(Boolean supportFido) {
        this.supportFido = supportFido;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LoginRequest setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LoginRequest setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getBrowserFp() {
        return browserFp;
    }

    public LoginRequest setBrowserFp(String browserFp) {
        this.browserFp = browserFp;
        return this;
    }

    public String getHmac() {
        return hmac;
    }

    public LoginRequest setHmac(String hmac) {
        this.hmac = hmac;
        return this;
    }
}
