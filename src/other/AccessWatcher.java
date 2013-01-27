package other;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AccessWatcher {
	private AccessWatcher() {
		throw new AssertionError();
	}

	public static boolean userAuthorized(String roleName) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// if user has sufficient privileges, then he's okay
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals(roleName)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static String getUserName() {
		String username = "admin";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			User user = (User) authentication.getPrincipal();
			username = user.getUsername();
		}
		return username;
	}

}
