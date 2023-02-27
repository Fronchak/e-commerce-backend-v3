package com.fronchak.e_commerce_v3.dtos.brand;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BrandInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Brand's name cannot be empty")
	private String name;
	
	@Pattern(regexp = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)", 
			message = "Invalid brand's image url")
	@NotNull(message = "Brand's image cannot be empty")
	private String imgUrl;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
