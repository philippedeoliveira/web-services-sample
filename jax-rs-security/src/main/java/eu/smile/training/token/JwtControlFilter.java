package eu.smile.training.token;

import java.io.IOException;
import java.security.Key;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;

@Provider
@JwtControl
@Priority(Priorities.AUTHENTICATION)
public class JwtControlFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		String token = null;

		try {

			// Extract the token from the HTTP Authorization header.
			token = authorizationHeader.substring("Bearer".length()).trim();

			// Validate the token, throw exception if not valid.
			Key key = JwtFactory.getMyApiKey();
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);

		} catch (Exception e) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}