package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "curvepoint")
@Data
public class CurvePoint {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Invalid numeric format")
	 @Column(name = "curveid")
	private Integer curveId;
    
	 @Column(name = "asofdate")
	private Timestamp asOfDate;
	 
	 @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "Invalid numeric format")
	private Double term;
	
	 @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "Invalid numeric format")
	private Double value;
	
	 @Column(name = "creationdate")
	private Timestamp creationDate;
}
