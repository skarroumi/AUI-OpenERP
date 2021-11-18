package ma.aui.openerp.services.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){

        http.cors().disable().csrf().disable();

        http.authorizeExchange(authorizeExchangeSpec ->
                authorizeExchangeSpec.anyExchange().authenticated());

        http.addFilterBefore(new SecurityFilter(), SecurityWebFiltersOrder.HTTP_BASIC);

        return http.build();
    }
}
