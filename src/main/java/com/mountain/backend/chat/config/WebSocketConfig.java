package com.mountain.backend.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker   // stomp 를 사용할 수 있게 annotaion 사용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*") // 클라리언트는 다른 origin 이므로 cors 오류 방지를 위해 setAllowedOrigins 를 미리 사용하여 허용할 origin 을 등록해둔다.
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/sub");    // /sub 가 prefix 로 붙은 destination 의 클라이언트에게 메세지를 보낼 수 있도록 simpleBroker 를 등록한다.
        registry.setApplicationDestinationPrefixes("/pub"); // /pub 가 prefix 로 붙은 메세지들은 @MessageMapping 이 붙은 method 로 바운드된다.
    }
}
