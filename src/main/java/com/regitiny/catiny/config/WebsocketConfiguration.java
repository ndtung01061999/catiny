package com.regitiny.catiny.config;

import com.regitiny.catiny.security.AuthoritiesConstants;
import com.regitiny.catiny.security.SecurityUtils;
import com.regitiny.catiny.tools.exception.constant.StringPool;
import java.security.Principal;
import java.util.*;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.*;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import tech.jhipster.config.JHipsterProperties;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    public static final String IP_ADDRESS = "IP_ADDRESS";

    private final JHipsterProperties jHipsterProperties;

    public WebsocketConfiguration(JHipsterProperties jHipsterProperties) {
        this.jHipsterProperties = jHipsterProperties;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String[] allowedOrigins = Optional
            .ofNullable(jHipsterProperties.getCors().getAllowedOrigins())
            .map(origins -> origins.toArray(new String[0]))
            .orElse(new String[0]);
        registry
            .addEndpoint("/websocket/tracker")
            .setHandshakeHandler(defaultHandshakeHandler())
            .setAllowedOrigins(allowedOrigins)
            .withSockJS()
            .setInterceptors(httpSessionHandshakeInterceptor());
        registry
            .addEndpoint("/websocket/message")
            .setHandshakeHandler(defaultHandshakeHandler())
            .setAllowedOrigins(allowedOrigins)
            .withSockJS()
            .setInterceptors(httpSessionHandshakeInterceptor());
        registry
            .addEndpoint("/websocket/live-streaming")
            .setHandshakeHandler(defaultHandshakeHandler())
            .setAllowedOrigins(allowedOrigins)
            .withSockJS()
            .setInterceptors(httpSessionHandshakeInterceptor());
        registry
            .addEndpoint("/websocket/video-call")
            .setHandshakeHandler(defaultHandshakeHandler())
            .setAllowedOrigins(allowedOrigins)
            .withSockJS()
            .setInterceptors(httpSessionHandshakeInterceptor());
    }

    @Bean
    public HandshakeInterceptor httpSessionHandshakeInterceptor() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(
                ServerHttpRequest request,
                ServerHttpResponse response,
                WebSocketHandler wsHandler,
                Map<String, Object> attributes
            ) throws Exception {
                if (request instanceof ServletServerHttpRequest) {
                    ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
                    attributes.put(IP_ADDRESS, servletRequest.getRemoteAddress());
                }
                return true;
            }

            @Override
            public void afterHandshake(
                ServerHttpRequest request,
                ServerHttpResponse response,
                WebSocketHandler wsHandler,
                Exception exception
            ) {}
        };
    }

    @Override
    public void configureClientInboundChannel(@Nonnull ChannelRegistration registration) {
        WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(registration);
        registration.interceptors(
            new ChannelInterceptor() {
                /**
                 *
                 * @param message
                 * @param channel
                 * @return
                 */
                @Override
                public Message<?> preSend(@Nonnull Message<?> message, @Nonnull MessageChannel channel) {
                    var accessor = StompHeaderAccessor.wrap(message);
                    var username = Objects.requireNonNull(accessor.getUser()).getName();
                    var destination = Objects.requireNonNull(accessor.getDestination());

                    var commandSubscribeCheck = StompCommand.SUBSCRIBE.equals(accessor.getCommand());
                    if (commandSubscribeCheck) {
                        if (destination.contains("/" + username + "/")) return ChannelInterceptor.super.preSend(message, channel);

                        // don't subscribe /topic/**
                        var destinationRegex = "/topic/[[\\w-]+/]*[*]{1,2}";
                        var destinationRegexCheck = Pattern.matches(destinationRegex, accessor.getDestination());
                        if (destinationRegexCheck) return null;
                    }

                    return ChannelInterceptor.super.preSend(message, channel);
                }
            }
        );
    }

    @Override
    public void configureWebSocketTransport(@Nonnull WebSocketTransportRegistration registry) {
        WebSocketMessageBrokerConfigurer.super.configureWebSocketTransport(registry);
        registry.setSendBufferSizeLimit(2 * 1024 * 1024);
        registry.setMessageSizeLimit(2 * 1024 * 1024);
        registry.setSendTimeLimit(10 * 1000);
    }

    private DefaultHandshakeHandler defaultHandshakeHandler() {
        return new DefaultHandshakeHandler() {
            @Override
            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                Principal principal = request.getPrincipal();
                if (principal == null) {
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ANONYMOUS));
                    principal = new AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous", authorities);
                }
                return principal;
            }
        };
    }
}
