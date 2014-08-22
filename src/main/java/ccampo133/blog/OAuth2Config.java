package ccampo133.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class OAuth2Config {

    // OAuth2 resource server setup/configuration.
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
			resources.resourceId("restful-blog");
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
			http.requestMatchers()
					.antMatchers("/**")
                    .anyRequest();
        }
    }

    // OAuth2 authorization server setup/configuration.
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

        // Injecting an AuthenticationManager bean allows for the "password" grant type to be enabled.
        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(new InMemoryTokenStore())
                    .approvalStore(approvalStore())
                    .authenticationManager(authenticationManager);
                    // Configure OAuth2 custom paths. Default Spring Security OAuth2 paths are listed at:
                    // http://projects.spring.io/spring-security-oauth/docs/oauth2.html
                    //.pathMapping("/oauth/authorize", "/api/oauth/authorize")
                    //.pathMapping("/oauth/token", "/api/oauth/token")
                    //.pathMapping("/oauth/confirm_access", "/api/oauth/confirm_access")
                    //.pathMapping("/oauth/error", "/api/oauth/error");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                        .withClient("restclient")
                            .resourceIds("restful-blog")
                            .authorizedGrantTypes("authorization_code", "implicit", "password")
                            .authorities("ROLE_CLIENT")
                            .scopes("read")
                            .secret("secret")
                            .redirectUris("https://www.getpostman.com/oauth2/callback")
                            .autoApprove(true);
        }

        @Bean
        public ApprovalStore approvalStore() {
            return new InMemoryApprovalStore();
        }
    }
}
