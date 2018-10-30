package cn.com.yusys.yusp.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private static final String WSUSERS = "ws-users";
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	public void addUser(String name,String sessionId){
		BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(WSUSERS);
		boundHashOperations.put(name, sessionId);
	}
	
	public void deleteUser(String name){
		BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(WSUSERS);
		boundHashOperations.delete(name);
	}
	
	public String getSessionId(String name){
		BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(WSUSERS);
		return boundHashOperations.get(name);
	}
	
	public List<String> listSessionIds(){
		BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(WSUSERS);
		List<String> values = new ArrayList<String>();
		for(String key:boundHashOperations.keys()){
			System.out.println(key);
			values.add(boundHashOperations.get(key));
		}
		return values;
	}
}
