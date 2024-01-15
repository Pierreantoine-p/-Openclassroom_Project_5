package com.nnk.springboot.domain;


import jakarta.persistence.Column;
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
	@Column(name = "bidlistid")
	 private Integer BidListId;
    @NotBlank(message = "Account is mandatory")
	 private String account;
    @NotBlank(message = "Type is mandatory")
	 private String type;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 @Column(name = "bidquantity")
	 private Double bidQuantity;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 @Column(name = "askquantity")
	 private Double askQuantity;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 private Double bid;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
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
