package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
@Data
public class CurvePoint {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
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
