package com.nnk.springboot.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
@Data
public class BidList {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private Integer BidListId;
    @NotBlank(message = "Account is mandatory")
	 private String account;
    @NotBlank(message = "Type is mandatory")
	 private String type;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double bidQuantity;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double askQuantity;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double bid;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double ask;
	 private String benchmark;
	 private Timestamp bidListDate;
	 private String commentary;
	 private String security;
	 private String status;
	 private String trader;
	 private String book;
	 private String creationName;
	 private Timestamp creationDate;
	 private String revisionName;
	 private Timestamp revisionDate;
	 private String dealName;
	 private String dealType;
	 private String sourceListId;
	 private String side;
	
}
