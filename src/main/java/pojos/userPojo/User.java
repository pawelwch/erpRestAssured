package pojos.userPojo;

import pojos.factoryPojo.Factory;

public class User {

    private String mfaStatus;
    private Factory factory;
    private String createdAt;
    private Roles[] roles;
    private String shouldResetPassword;
    private String id;
    private String userType;
    private String lastPasswordChange;
    private String passwordExpirationInDays;
    private String email;
    private UserProfile userProfile;
    private String status;

}
