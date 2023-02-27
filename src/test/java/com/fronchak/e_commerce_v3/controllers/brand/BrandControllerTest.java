package com.fronchak.e_commerce_v3.controllers.brand;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fronchak.e_commerce_v3.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v3.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v3.factories.BrandMocks;
import com.fronchak.e_commerce_v3.repositories.BrandRepository;
import com.fronchak.e_commerce_v3.services.BrandService;
import com.fronchak.e_commerce_v3.util.ControllerAsserts;
import com.fronchak.e_commerce_v3.util.TestConstrants;
import com.fronchak.e_commerce_v3.util.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
public class BrandControllerTest {

	protected static final Long VALID_ID = 1L;
	protected static final Long INVALID_ID = 2L;
	protected static final MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;
	
	private static final String CLIENT_USERNAME = "client@gmail.com";
	private static final String CLIENT_PASSWORD = "123456";
	private static final String WORKER_USERNAME = "worker@gmail.com";
	private static final String WORKER_PASSWORD = "123456";
	private static final String ADMIN_USERNAME = "admin@gmail.com";
	private static final String ADMIN_PASSWORD = "123456";
	
	protected String accessToken;
	
	@Autowired
	protected ObjectMapper mapper;
	
	@Autowired
	protected MockMvc mockMvc;
		
	@Autowired
	protected TokenUtil tokenUtil;
	
	@MockBean
	protected BrandService service;
	
	@MockBean
	protected BrandRepository repository;
	
	private BrandOutputDTO outputDTO;
	
	protected void getClientToken() throws Exception {
		accessToken = tokenUtil.obtainAccessToken(mockMvc, CLIENT_USERNAME, CLIENT_PASSWORD);
	}
	
	protected void getWorkerToken() throws Exception {
		accessToken = tokenUtil.obtainAccessToken(mockMvc, WORKER_USERNAME, WORKER_PASSWORD);
	}
	
	protected void getAdminToken() throws Exception {
		accessToken = tokenUtil.obtainAccessToken(mockMvc, ADMIN_USERNAME, ADMIN_PASSWORD);
	}
	
	@BeforeEach
	void setUp() {
		outputDTO = BrandMocks.mockBrandOutputDTO();
	}
	
	@Test
	public void findByIdShouldReturnSuccessWhenIdExists() throws Exception {
		when(service.findById(VALID_ID)).thenReturn(outputDTO);
		
		ResultActions result = mockMvc.perform(get("/brands/{id}", VALID_ID)
				.accept(MEDIA_TYPE));
		
		ControllerAsserts.assertSuccess(result);
		verify(service, times(1)).findById(VALID_ID);
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		when(service.findById(INVALID_ID)).thenThrow(ResourceNotFoundException.class);
		
		ResultActions result = mockMvc.perform(get("/brands/{id}", INVALID_ID)
				.accept(MEDIA_TYPE));
		
		ControllerAsserts.assertNotFound(result);
		verify(service, times(1)).findById(INVALID_ID);
	}
	
	@Test
	public void findAllShouldReturnSuccess() throws Exception {
		List<BrandOutputDTO> dtos = BrandMocks.mockBrandOutputDTOList();
		when(service.findAll()).thenReturn(dtos);
		
		ResultActions result = mockMvc.perform(get("/brands")
				.accept(MEDIA_TYPE));
		
		ControllerAsserts.assertSuccess(result);
		verify(service, times(1)).findAll();
	}
	
	@Test
	public void deleteShouldReturnUnauthorizedWhenNotLogged() throws Exception {
		ResultActions result = mockMvc.perform(delete("/brands/{id}", VALID_ID)
				.accept(MEDIA_TYPE));
		
		ControllerAsserts.assertUnauthorized(result);
		verify(service, never()).deleteById(any());
	}
	
	@Test
	public void deleteShouldReturnForbiddenWhenWorkerIsLogged() throws Exception {
		getWorkerToken();
		
		ResultActions result = mockMvc.perform(delete("/brands/{id}", VALID_ID)
				.header(TestConstrants.AUTHORIZATION, TestConstrants.BEARER + " " + accessToken)
				.accept(MEDIA_TYPE));
		
		ControllerAsserts.assertForbidden(result);
		verify(service, never()).deleteById(VALID_ID);
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenAdminIsLogged() throws Exception {
		getAdminToken();
		
		ResultActions result = mockMvc.perform(delete("/brands/{id}", VALID_ID)
				.header(TestConstrants.AUTHORIZATION, TestConstrants.BEARER + " " + accessToken)
				.accept(MEDIA_TYPE));
		
		ControllerAsserts.assertNoContent(result);
		verify(service, times(1)).deleteById(VALID_ID);
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenAdminIsLoggedButIdDoesNotExist() throws Exception {
		getAdminToken();
		doThrow(ResourceNotFoundException.class).when(service).deleteById(INVALID_ID);
		
		ResultActions result = mockMvc.perform(delete("/brands/{id}", INVALID_ID)
				.header(TestConstrants.AUTHORIZATION, TestConstrants.BEARER + " " + accessToken)
				.accept(MEDIA_TYPE));
		
		ControllerAsserts.assertNotFound(result);
		verify(service, times(1)).deleteById(INVALID_ID);
	}
}
