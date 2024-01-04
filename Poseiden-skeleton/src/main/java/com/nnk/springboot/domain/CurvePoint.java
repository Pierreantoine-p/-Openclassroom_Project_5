package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Data
public class CurvePoint {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
    @NotBlank(message = "must not be null")
	private Integer curveId;
	private Timestamp asOfDate;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	private Double term;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	private Double value;
	private Timestamp creationDate;
}
