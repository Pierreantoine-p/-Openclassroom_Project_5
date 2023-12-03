package com.nnk.springboot.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
@Data
public class BidList {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	 private Integer BidListId;
    @NotBlank(message = "account is mandatory")
	 private String account;
    @NotBlank(message = "type is mandatory")
	 private String type;
    @NotBlank(message = "bidQuantity is mandatory")
	 private Double bidQuantity;
	 private Double askQuantity;
	 private Double bid;
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
