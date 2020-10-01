package com.ma.pedidos.restControllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ma.pedidos.MainApp;
import com.ma.pedidos.daoControllers.productos.IProductosService;
import com.ma.pedidos.entities.Producto;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes=MainApp.class)
@AutoConfigureMockMvc
public class ProductosRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private IProductosService productosService;
	
	/*
	 * Pruebo la correcta creación de un producto
	 */
	@Test
	void whenCreateProductThenReturnProduct() throws Exception {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		Producto prod = new Producto();
		prod.setNombre("Pizza for Test");
		prod.setDescripcionCorta("Una pizza que no tiene salsa no queso, solo sirve para testear la app");
		prod.setPrecioUnitario(250F);
	  
		mockMvc.perform(MockMvcRequestBuilders.post("/productos")
				.contentType("application/json;charset=UTF-8")
				.content(objectMapper.writeValueAsString(prod)))
	        	.andExpect(MockMvcResultMatchers.status().isCreated())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(prod.getNombre()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.descripcionCorta").value(prod.getDescripcionCorta()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.precioUnitario").value(prod.getPrecioUnitario()));
	}
	
	/*
	 * Pruebo la creación de un producto sin uno de los campos obligatorios
	 */
	@Test
	void whenCreateInvalidProductThenReturnError() throws Exception {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		
		Producto prod = new Producto();
		prod.setNombre("Pizza for Test");
		prod.setDescripcionCorta("Una pizza que no tiene salsa no queso, solo sirve para testear la app");
	  
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/productos")
				.contentType("application/json;charset=UTF-8")
				.content(objectMapper.writeValueAsString(prod))).andReturn();
		
		assertTrue(result.getResponse().getStatus() == MockHttpServletResponse.SC_BAD_REQUEST);
		assertTrue(result.getResponse().getContentAsString().contains("precioUnitario"));
	}
	
	/*
	 * Pruebo actualizar un producto que existe
	 */
	@Test
	void whenUpdateExistingProductThenReturnProduct() throws Exception {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		
		Producto prod = productosService.findById(1L).get();
		prod.setNombre("Pizza con nombre modificado");
		
		mockMvc.perform(MockMvcRequestBuilders.put("/productos/{id}",1L)
				.contentType("application/json;charset=UTF-8")
				.content(objectMapper.writeValueAsString(prod)))
	        	.andExpect(MockMvcResultMatchers.status().isOk())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(prod.getNombre()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.descripcionCorta").value(prod.getDescripcionCorta()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.precioUnitario").value(prod.getPrecioUnitario()));
	}
	
	/*
	 * Pruebo actualizar un producto que no existe
	 */
	@Test
	void whenUpdateNotExistingProductThenReturnNotFound() throws Exception {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		
		Producto prod = new Producto();
		prod.setNombre("Pizza que no existe para Test");
		prod.setDescripcionCorta("Una pizza que no tiene salsa no queso, solo sirve para testear la app");
		prod.setPrecioUnitario(250F);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/productos/{id}",33L)
				.contentType("application/json;charset=UTF-8")
				.content(objectMapper.writeValueAsString(prod)))
	        	.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	/*
	 * Pruebo eliminar un producto que existe en la DB y no tiene
	 * pedidos asociados
	 */
	@Test
	void whenDeleteExistingProductThenReturnNoContent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/productos/{id}",3L))
	        	.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	/*
	 * Pruebo eliminar un producto que no existe
	 */
	@Test
	void whenDeleteNotExistingProductThenReturnNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/productos/{id}",25L))
	        	.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	/*
	 * Pruebo eliminar un producto que existe pero tiene pedidos asociados
	 */
	@Test
	void whenDeleteExistingAndAssociatedProductThenReturnBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/productos/{id}",1L))
	        	.andExpect(MockMvcResultMatchers.status().isBadRequest())
	        	.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("El producto 1 tiene pedidos asociados"));
	}
	
}
