package cn.com.yusys.yusp.example.web.rest;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import cn.com.yusys.yusp.example.config.websocket.Constance;
import cn.com.yusys.yusp.example.service.UserService;
import cn.com.yusys.yusp.example.web.dto.Message;

@Controller
public class GroupMessageResource {	
	
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private UserService userService;
	
	@MessageMapping("/sendall")
    public void say(Message message) throws Exception {
		List<String> users = userService.listSessionIds();
		for(String user:users){
			System.out.println(Constance.queue_pre+user);
			amqpTemplate.convertAndSend("", Constance.queue_pre+user, message.getMessage());
		}
    }
}
