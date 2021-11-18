package ma.aui.openerp.services.leave.configuration;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

public class TokenInspectionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            String authorizationHeader = ((HttpServletRequest) request).getHeader("Authorization");
            String strToken = authorizationHeader.substring(7);

            /**  New changes begin */
            // localhost:8080 is the keycloak server hosted url
            JwkProvider provider = new UrlJwkProvider(new URL("http://localhost:8080/auth/realms/AUI-OpenERP-realm/protocol/openid-connect/certs"));
            DecodedJWT decodedJWT = JWT.decode(strToken);
            Jwk jwk = provider.get(decodedJWT.getKeyId());
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(decodedJWT);

            /**  New changes end */

            String role = decodedJWT.getClaim("user_role").toString();

            if(role != null) {

                role = StringUtils.trimLeadingCharacter(role, '"');
                role = StringUtils.trimTrailingCharacter(role, '"');

                String finalRole = role;

                List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>(1) {{
                    add(new SimpleGrantedAuthority(finalRole));
                }};

                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), "", authorities);

                SecurityContextHolder.getContext().setAuthentication(token);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        chain.doFilter(request, response);

        SecurityContextHolder.clearContext();
    }
}
