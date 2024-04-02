package com.nnk.springboot.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
@Data
public class BidList {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "bidlistid")
	 private Integer BidListId;
	
    @NotBlank(message = "Account is mandatory")
	 private String account;
    
    @NotBlank(message = "Type is mandatory")
	 private String type;
    
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "Only digits are allowed")
    @Column(name = "bidquantity")
    private Double bidQuantity;
	 
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "Only digits are allowed")
	@Column(name = "askquantity")
	private Double askQuantity;
	 
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "Only digits are allowed")
	 private Double bid;
	 
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "Only digits are allowed")
	private Double ask;
	 
	 private String benchmark;
	 
	 
	 @Column(name = "bidlistdate")
	 private Timestamp bidListDate;
	 
	 private String commentary;
	 private String security;
	 private String status;
	 private String trader;
	 private String book;
	 
	 @Column(name = "creationname")
	 private String creationName;
	 
	 @Column(name = "creationdate")
	 private Timestamp creationDate;
	 
	 @Column(name = "revisionname")
	 private String revisionName;
	 
	 @Column(name = "revisiondate")
	 private Timestamp revisionDate;
	 
	 @Column(name = "dealname")
	 private String dealName;
	 
	 @Column(name = "dealtype")
	 private String dealType;
	 
	 @Column(name = "sourcelistid")
	 private String sourceListId;
	 
	 private String side;
	
}
