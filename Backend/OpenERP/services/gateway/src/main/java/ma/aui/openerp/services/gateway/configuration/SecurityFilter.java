package ma.aui.openerp.services.gateway.configuration;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.AbstractServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

public class SecurityFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        Logger logger = LoggerFactory.getLogger(this.getClass());
        try {

            String authorizationHeader = (((AbstractServerHttpRequest) exchange.getRequest()).getHeaders()).getFirst("Authorization");
            String strToken = authorizationHeader.substring(7);

            JwkProvider provider = new UrlJwkProvider(new URL("http://localhost:8080/auth/realms/AUI-OpenERP-realm/protocol/openid-connect/certs"));
            DecodedJWT decodedJWT = JWT.decode(strToken);
            Jwk jwk = provider.get(decodedJWT.getKeyId());

            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(decodedJWT);

            String role = decodedJWT.getClaim("user_role").toString();

            if (role != null) {

                role = StringUtils.trimLeadingCharacter(role, '"');
                role = StringUtils.trimTrailingCharacter(role, '"');

                String finalRole = role;

                List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>(1) {{
                    add(new SimpleGrantedAuthority(finalRole));
                }};

                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), "", authorities);

                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(token));
            }
        } catch (Exception e) {
            logger.error("Unauthenticated User");
            //e.printStackTrace();
        }
        return chain.filter(exchange);

    }
}

