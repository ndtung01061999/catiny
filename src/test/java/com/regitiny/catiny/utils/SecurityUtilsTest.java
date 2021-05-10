package com.regitiny.catiny.utils;

import com.regitiny.catiny.security.AuthoritiesConstants;
import com.regitiny.catiny.security.SecurityUtils;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtilsTest {

    private static SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

    public static String jwtAdmin() {
        var authoritiesValue = new ArrayList<String>();
        authoritiesValue.add(AuthoritiesConstants.ADMIN);
        authoritiesValue.add(AuthoritiesConstants.USER);
        authoritiesValue.add(AuthoritiesConstants.ANONYMOUS);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authoritiesValue.forEach(authorValue -> authorities.add(new SimpleGrantedAuthority(authorValue)));

        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin", authorities));
        SecurityContextHolder.setContext(securityContext);
        return SecurityUtils.getCurrentUserJWT().orElse("");
    }
}
