package cn.com.yusys.yusp.example.web.rest;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import cn.com.yusys.yusp.example.config.websocket.Constance;
import cn.com.yusys.yusp.example.service.UserService;
import cn.com.yusys.yusp.example.web.dto.Message;

@Controller
public class PointMessageResource3 {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private UserService userService;

	@MessageMapping("/point2point")
	public void point2point3(Message message, SimpMessageHeaderAccessor headerAccessor) {
		String userId = userService.getSessionId(message.getTo());
		System.out.println(Constance.queue_pre+userId);
		amqpTemplate.convertAndSend("", Constance.queue_pre+userId, message.getMessage());
	}
}
