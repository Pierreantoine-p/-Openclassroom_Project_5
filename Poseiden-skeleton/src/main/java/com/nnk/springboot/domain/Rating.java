package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "rating")
@Data
public class Rating {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Type is mandatory")
	 @Column(name = "moodysrating")
	private String moodysRating;
	 
	@NotBlank(message = "Type is mandatory")
	 @Column(name = "sandprating")
	private String sandPRating;
	 
	@NotBlank(message = "Type is mandatory")
	 @Column(name = "fitchrating")
	private String fitchRating;
	 
	 @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Invalid numeric format")
	 @Column(name = "ordernumber")
	private Integer orderNumber;
}
