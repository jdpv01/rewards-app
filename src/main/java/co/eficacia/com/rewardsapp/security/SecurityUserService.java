package co.eficacia.com.rewardsapp.security;

public interface SecurityUserService {

    String validatePasswordResetToken(String token);

}
