package jeff.dev.smartstock.service;

import jeff.dev.smartstock.client.AuthClient;
import jeff.dev.smartstock.client.dto.AuthRequest;
import jeff.dev.smartstock.config.AppConfig;
import jeff.dev.smartstock.exception.SmartStockException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

	private static final String GRANT_TYPE = "client_credentials";
	private static String token;
	private static LocalDateTime expiresIn;

	private final AuthClient authClient;
	private final AppConfig appConfig;

	public AuthService(AuthClient authClient, AppConfig appConfig) {
		this.authClient = authClient;
		this.appConfig = appConfig;
	}

	public String getToken() {
		if (token == null) {
			gerenateToken();
		} else if (expiresIn.isBefore(LocalDateTime.now())) {
			gerenateToken();
		}

		return token;
	}

	private void gerenateToken() {

		var request = new AuthRequest(
				GRANT_TYPE,
				appConfig.getClientId(),
                appConfig.getClientSecret()
		);

		var response = authClient.authenticate(request);

		if (!response.getStatusCode().is2xxSuccessful()) {
			throw new SmartStockException("Cannot generate token, status: " +
					response.getStatusCode() +
					response.getBody());
		}

		token = response.getBody().accessToken();
		expiresIn = LocalDateTime.now().plusSeconds(response.getBody().expiresIn());
	}
}
