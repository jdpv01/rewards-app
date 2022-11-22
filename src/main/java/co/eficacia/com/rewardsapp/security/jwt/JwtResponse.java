package co.eficacia.com.rewardsapp.security.jwt;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private UUID id;
	private String email;
	private List<String> roleList;

	public JwtResponse(String accessToken, UUID id, String email, List<String> roleList) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
		this.roleList = roleList;
	}
}
