package com.regitiny.catiny.web.websocket;

import com.regitiny.catiny.security.SecurityUtils;
import com.regitiny.catiny.tools.utils.EntityDefaultPropertiesServiceUtils;
import com.regitiny.catiny.web.websocket.dto.ActivityDTO;
import com.regitiny.catiny.web.websocket.dto.VideoCallDTO;
import io.swagger.annotations.ApiModelProperty;
import java.security.Principal;
import jodd.util.StringPool;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Log4j2
@Controller
public class WebSocket implements ApplicationListener<SessionDisconnectEvent> {

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocket(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/topic/video-call/activity")
    public VideoCallDTO sendActivity(@Payload VideoCallDTO x, StompHeaderAccessor stompHeaderAccessor, Principal principal) {
        //    byte[] decodedBytes = Base64.getDecoder().decode(x.getData().split(",")[2]);
        //    File tempFile = new File("/data/test/test.webm");
        //    try (FileOutputStream out = new FileOutputStream(tempFile, true))
        //    {
        //      log.debug("create new file output VideoCall (webcam) is: {}", tempFile.createNewFile());
        //      out.write(decodedBytes);
        //    }
        //    catch (Exception e)
        //    {
        //      log.debug(e);
        //    }

        messagingTemplate.convertAndSend("/topic/video-call/group/admin/" + x.getDetails(), x);
        //    messagingTemplate.convertAndSendToUser("user","/topic/video-call/group2/"+x.getDetails(), x,stompHeaderAccessor.getMessageHeaders());
        //    messagingTemplate.convertAndSend("/topic/video-call/group/"+x.getDetails(), x);
        return x;
    }

    @SubscribeMapping("/topic/video-call/group2/*")
    public Object s() {
        log.debug("SecurityUtils.getCurrentUserLogin().get()");

        return null;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        log.debug(SecurityUtils.getCurrentUserLogin().get());
    }
}
