package org.anb.mii.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JsonUtils {

	ObjectMapper objectMapper= getMapper();
	
	ObjectNode parentNode = objectMapper.createObjectNode();
	
	@Bean
	public ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return  mapper;
	}
	
	public  JsonNode setData(String value)
	{
		
		 parentNode.put("data", value);
		 return parentNode;
	}
	public  JsonNode setError(String value)
	{
		parentNode.put("error", true);
		 parentNode.put("message", value);
		 return parentNode;
	}
	
	public String print(Object value) 
	{		
		try
		{
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
		}
		catch(JsonProcessingException e)
		{
			log.error(e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	
	
}
