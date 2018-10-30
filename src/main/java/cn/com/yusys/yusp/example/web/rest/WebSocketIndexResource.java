package cn.com.yusys.yusp.example.web.rest;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebSocketIndexResource {
	
	@GetMapping("/2")
    public ModelAndView PointToPoint(Map<String, Object> map) {
        ModelAndView res = new ModelAndView("PointToPoint2");
        return res;
    }	
}