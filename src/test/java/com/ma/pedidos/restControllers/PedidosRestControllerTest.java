package com.ma.pedidos.restControllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ma.pedidos.MainApp;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes=MainApp.class)
@AutoConfigureMockMvc
public class PedidosRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	/*
	 * Pruebo la correcta creación de un pedido
	 */
	@Test
	void whenCreateOrderThenReturnOrder() throws Exception {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		
		String jsonRequest = "{\r\n"
				+ "    \"direccion\": \"Igualdad 759\",\r\n"
				+ "    \"mail\": \"sebastianfrh@gmail\",\r\n"
				+ "    \"telefono\": \"3424210770\",\r\n"
				+ "    \"detalle\": [\r\n"
				+ "        {\r\n"
				+ "            \"producto\": 1,\r\n"
				+ "            \"cantidad\": 1\r\n"
				+ "        }\r\n"
				+ "        ,\r\n"
				+ "        {\r\n"
				+ "            \"producto\": 2,\r\n"
				+ "            \"cantidad\": 1\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
				.contentType("application/json;charset=UTF-8")
				.content(jsonRequest))
	        	.andExpect(MockMvcResultMatchers.status().isCreated())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}
	
	/*
	 * Pruebo la búsqueda por fecha
	 * en primer lugar creo dos pedidos
	 */
	@Test
	void whenListOrderThenReturnList() throws Exception {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String jsonRequest1 = "{\r\n"
				+ "    \"direccion\": \"Igualdad 759\",\r\n"
				+ "    \"mail\": \"sebastianfrh@gmail\",\r\n"
				+ "    \"telefono\": \"3424210770\",\r\n"
				+ "    \"detalle\": [\r\n"
				+ "        {\r\n"
				+ "            \"producto\": 1,\r\n"
				+ "            \"cantidad\": 1\r\n"
				+ "        }\r\n"
				+ "        ,\r\n"
				+ "        {\r\n"
				+ "            \"producto\": 2,\r\n"
				+ "            \"cantidad\": 1\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		String jsonRequest2 = "{\r\n"
				+ "    \"direccion\": \"Igualdad 759\",\r\n"
				+ "    \"mail\": \"sebastianfrh@gmail\",\r\n"
				+ "    \"telefono\": \"3424210770\",\r\n"
				+ "    \"detalle\": [\r\n"
				+ "        {\r\n"
				+ "            \"producto\": 1,\r\n"
				+ "            \"cantidad\": 2\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
				.contentType("application/json;charset=UTF-8")
				.content(jsonRequest1))
	        	.andExpect(MockMvcResultMatchers.status().isCreated())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
				.contentType("application/json;charset=UTF-8")
				.content(jsonRequest2))
	        	.andExpect(MockMvcResultMatchers.status().isCreated())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/pedidos")
				.contentType("application/json;charset=UTF-8")
				.param("fecha", sdf.format(new Date())))
	        	.andExpect(MockMvcResultMatchers.status().isOk())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
	}

}
