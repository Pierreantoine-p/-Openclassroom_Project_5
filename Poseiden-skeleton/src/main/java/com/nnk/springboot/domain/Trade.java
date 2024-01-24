package com.nnk.springboot.domain;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Entity
@Table(name = "trade")
@Data
public class Trade {
	
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 @Column(name = "tradeid")
	 private Integer tradeId;
	 private String account;
	 private String type;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 @Column(name = "buyquantity")
	 private Double buyQuantity;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 @Column(name = "sellquantity")
	 private Double sellQuantity;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 @Column(name = "buyprice")
	 private Double buyPrice;
	 @Pattern(regexp  = "^\\\\d+(\\\\.\\\\d+)?$")
	 @Column(name = "sellprice")
	 private Double sellPrice;
	 private String benchmark;
	 @Column(name = "tradedate")
	 private Timestamp tradeDate;
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
