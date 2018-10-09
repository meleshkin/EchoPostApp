package echopostapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EchoPostController {
    private static final String TOPIC = "/topic/echopostapp";

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> test(@RequestBody String body) {
        String bodyEcho = "";
        if (body != null) {
            bodyEcho = body;
        }
        ServerMessage message = new ServerMessage(bodyEcho);
        messageTemplate.convertAndSend(TOPIC, message);
        return ResponseEntity.noContent().build();
    }

}
