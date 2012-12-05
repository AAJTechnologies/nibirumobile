package ar.com.oxen.nibiru.mobile.core.api.data.security;

/**
 * User domain object.
 */
public interface User {
	String getUsername();

	String getPasswordHash();

	String getFirstName();

	String getLastName();

	void setUsername(String username);

	void setPasswordHash(String passwordHash);

	void setFirstName(String firstName);

	void setLastName(String lastName);
}
