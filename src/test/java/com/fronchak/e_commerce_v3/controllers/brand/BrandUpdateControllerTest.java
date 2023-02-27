package com.fronchak.e_commerce_v3.controllers.brand;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.fronchak.e_commerce_v3.dtos.brand.BrandUpdateDTO;
import com.fronchak.e_commerce_v3.entities.Brand;
import com.fronchak.e_commerce_v3.exceptions.ResourceNotFoundException;
import com.fronchak.e_commerce_v3.factories.BrandMocks;
import com.fronchak.e_commerce_v3.util.ControllerAsserts;
import com.fronchak.e_commerce_v3.util.TestConstrants;

public class BrandUpdateControllerTest extends BrandControllerTest {

	private BrandUpdateDTO inputDTO;
	private String body;
	private ResultActions result;
	
	@BeforeEach
	void setUp() {
		inputDTO = BrandMocks.mockBrandUpdateDTO();
	}
	
	private void convertInputDTOToJson() throws Exception {
		body = mapper.writeValueAsString(inputDTO);
	}
	
	
	private void performPutWithoutToken(Long id) throws Exception {
		result = mockMvc.perform(put("/brands/{id}", id)
				.accept(MEDIA_TYPE)
				.content(body)
				.contentType(MEDIA_TYPE));
	}
	
	private void performPutWithToken(Long id) throws Exception {
		result = mockMvc.perform(put("/brands/{id}", id)
				.header(TestConstrants.AUTHORIZATION, TestConstrants.BEARER + " " + accessToken)
				.accept(MEDIA_TYPE)
				.content(body)
				.contentType(MEDIA_TYPE));
	}
	
	@Test
	public void updateShouldReturnUnauthorizedWhenNoOneIsLogged() throws Exception {
		convertInputDTOToJson();

		performPutWithoutToken(VALID_ID);
		
		ControllerAsserts.assertUnauthorized(result);
		verify(service, never()).update(any(), any());
	}
	
	@Test
	public void updateShouldReturnForbiddenWhenClientIsLogged() throws Exception {
		convertInputDTOToJson();
		getClientToken();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertForbidden(result);
		verify(service, never()).update(any(), any());
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdIsInvalidAndWorkerIsLogged() throws Exception {
		when(service.update(any(BrandUpdateDTO.class), eq(INVALID_ID))).thenThrow(ResourceNotFoundException.class);
		convertInputDTOToJson();
		getWorkerToken();
		
		performPutWithToken(INVALID_ID);
		
		ControllerAsserts.assertNotFound(result);
		verify(service, timeout(1)).update(any(BrandUpdateDTO.class), eq(INVALID_ID));
	}
	
	@Test
	public void updateShouldReturnUnprocessableEntityWhenNameIsNull() throws Exception {
		inputDTO.setName(null);
		getWorkerToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);;
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "name", "Brand's name cannot be empty");
		verify(service, never()).update(any(), any());
	}
	
	@Test
	public void updateShouldReturnUnprocessableEntityWhenNameIsEmptyAndWorkerIsLogged() throws Exception {
		inputDTO.setName(" ");
		getWorkerToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "name", "Brand's name cannot be empty");
		verify(service, never()).update(any(), any());
	}
	
	@Test
	public void updateShouldReturnUnprocessableEntityWhenImgUrlIsNullAndWorkerIsLogged() throws Exception {
		inputDTO.setImgUrl(null);
		getWorkerToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "imgUrl", "Brand's image cannot be empty");
		verify(service, never()).update(any(), any());
	}
	
	@Test
	public void updateShouldReturnUnprocessableEntityWhenImgUrlIsEmptyAndWorkerIsLogged() throws Exception {
		inputDTO.setImgUrl(" ");
		getWorkerToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "imgUrl", "Invalid brand's image url");
		verify(service, never()).update(any(), any());
	}
	
	
	@Test
	public void saveShouldReturnUnprocessableEntityWhenImgUrlIsNotAImageLinkAndWorkerIsLogged() throws Exception {
		inputDTO.setImgUrl("http://image01");
		getWorkerToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertUnprocessableEntity(result);
		ControllerAsserts.assertErrorMessage(result, "imgUrl", "Invalid brand's image url");
		verify(service, never()).update(any(), any());
	}
	
	@Test
	public void updateShouldReturnUnprocessableEntityWhenTryToUpdateTwoBrandsWithTheSameName() throws Exception {
		Brand entity = new Brand();
		entity.setId(10L);
		when(repository.findByName(inputDTO.getName())).thenReturn(entity);
		getWorkerToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertUnprocessableEntity(result);
		verify(service, never()).update(any(), any());
	}
	
	@Test
	public void updateShouldReturnSuccessWhenThereIsNoDuplicatesName() throws Exception {
		Brand entity = new Brand();
		entity.setId(VALID_ID);
		when(repository.findByName(inputDTO.getName())).thenReturn(entity);
		getWorkerToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertSuccess(result);
		verify(service, times(1)).update(any(), any());
	}
	
	
	@Test
	public void updateShouldReturnSuccessWhenWorkerIsLogged() throws Exception {
		getWorkerToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertSuccess(result);
		verify(service, times(1)).update(any(BrandUpdateDTO.class), eq(VALID_ID));
	}
	
	@Test
	public void updateShouldReturnSuccessWhenAdminIsLogged() throws Exception {
		getAdminToken();
		convertInputDTOToJson();
		
		performPutWithToken(VALID_ID);
		
		ControllerAsserts.assertSuccess(result);
		verify(service, times(1)).update(any(BrandUpdateDTO.class), eq(VALID_ID));
	}
}
