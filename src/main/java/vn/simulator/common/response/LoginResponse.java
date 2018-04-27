package vn.simulator.common.response;

public class LoginResponse {

    private String appId;
    private String userId;
    private String companyId;
    private String companyName;
    private Integer role;
    private String timezone;
    private String authToken;
    private Boolean useSystemPassword;
    private Boolean passwordExpired;
    private String secretCode;
    private String authMethods;
    private Long sessionTimeout;
    private String email;
    private String groupId;
    private Long lastSuccessLogin;
    private String uuid;

    public String getAppId() {
        return appId;
    }

    public LoginResponse setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public LoginResponse setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public LoginResponse setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LoginResponse setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public Integer getRole() {
        return role;
    }

    public LoginResponse setRole(Integer role) {
        this.role = role;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public LoginResponse setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public String getAuthToken() {
        return authToken;
    }

    public LoginResponse setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    public Boolean getUseSystemPassword() {
        return useSystemPassword;
    }

    public LoginResponse setUseSystemPassword(Boolean useSystemPassword) {
        this.useSystemPassword = useSystemPassword;
        return this;
    }

    public Boolean getPasswordExpired() {
        return passwordExpired;
    }

    public LoginResponse setPasswordExpired(Boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
        return this;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public LoginResponse setSecretCode(String secretCode) {
        this.secretCode = secretCode;
        return this;
    }

    public String getAuthMethods() {
        return authMethods;
    }

    public LoginResponse setAuthMethods(String authMethods) {
        this.authMethods = authMethods;
        return this;
    }

    public Long getSessionTimeout() {
        return sessionTimeout;
    }

    public LoginResponse setSessionTimeout(Long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public LoginResponse setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public Long getLastSuccessLogin() {
        return lastSuccessLogin;
    }

    public LoginResponse setLastSuccessLogin(Long lastSuccessLogin) {
        this.lastSuccessLogin = lastSuccessLogin;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public LoginResponse setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}
