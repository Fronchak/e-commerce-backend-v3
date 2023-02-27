package com.fronchak.e_commerce_v3.controllers.brand;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.fronchak.e_commerce_v3.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v3.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v3.entities.Brand;
import com.fronchak.e_commerce_v3.factories.BrandMocks;
import com.fronchak.e_commerce_v3.util.ControllerAsserts;
import com.fronchak.e_commerce_v3.util.TestConstrants;

public class BrandSaveControllerTest extends BrandControllerTest {

	private BrandInsertDTO inputDTO;
	private String body;

	private ResultActions result;
	
	@BeforeEach
	void setUp() {
		inputDTO = BrandMocks.mockBrandInsertDTO();
		when(service.save(any(BrandInsertDTO.class))).thenReturn(new BrandOutputDTO());
	}
	
	private void convertInputDTOToJson() throws Exception {
		body = mapper.writeValueAsString(inputDTO);
	}
	
	private void performPostWithoutToken() throws Exception {
		result = mockMvc.perform(post("/brands")
				.accept(MEDIA_TYPE)
				.content(body)
				.contentType(MEDIA_TYPE));
	}
	
	private void performPostWithToken() throws Exception {
		result = mockMvc.perform(post("/brands")
				.header(TestConstrants.AUTHORIZATION, TestConstrants.BEARER + " " + accessToken)
				.accept(MEDIA_TYPE)
				.content(body)
				.contentType(MEDIA_TYPE));
	}
	
	@Test
	public void saveShouldReturnAnauthorizedWhenThereIsNoOneLogged() throws Exception {
		convertInputDTOToJson();
		
		performPostWithoutToken();
		
		ControllerAsserts.assertUnauthorized(result);
		verify(service, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnForbiddenWhenClientIsLogged() throws Exception {
		getClientToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertForbidden(result);
		verify(service, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnUnprocessableEntityWhenNameIsNullAndWorkerIsLogged() throws Exception {
		inputDTO.setName(null);
		getWorkerToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "name", "Brand's name cannot be empty");
		verify(service, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnUnprocessableEntityWhenNameIsEmptyAndWorkerIsLogged() throws Exception {
		inputDTO.setName(" ");
		getWorkerToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "name", "Brand's name cannot be empty");
		verify(service, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnUnprocessableEntityWhenImgUrlIsNullAndWorkerIsLogged() throws Exception {
		inputDTO.setImgUrl(null);
		getWorkerToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "imgUrl", "Brand's image cannot be empty");
		verify(service, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnUnprocessableEntityWhenImgUrlIsEmptyAndWorkerIsLogged() throws Exception {
		inputDTO.setImgUrl(" ");
		getWorkerToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "imgUrl", "Invalid brand's image url");
		verify(service, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnUnprocessableEntityWhenImgUrlIsNotAImageLinkAndWorkerIsLogged() throws Exception {
		inputDTO.setImgUrl("http://image01");
		getWorkerToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "imgUrl", "Invalid brand's image url");
		verify(service, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnUnprocessableEntityWhenTryToSaveTwoBrandsWithTheSameName() throws Exception {
		when(repository.findByName(inputDTO.getName())).thenReturn(new Brand());
		getWorkerToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "name", "This brand is already registered");
		verify(service, never()).save(any());
	}
	
	@Test
	public void saveShouldReturnCreatedWhenWorkerIsLogged() throws Exception {
		getWorkerToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertCreated(result);
		verify(service, times(1)).save(any());
	}
	
	@Test
	public void saveShouldReturnCreatedWhenAdminIsLogged() throws Exception {
		getAdminToken();
		convertInputDTOToJson();
		
		performPostWithToken();
		
		ControllerAsserts.assertCreated(result);
		verify(service, times(1)).save(any());
	}
}
