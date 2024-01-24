package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "rating")
@Data
public class Rating {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	 @Column(name = "moodysrating")
	private String moodysRating;
	 @Column(name = "sandprating")
	private String sandPRating;
	 @Column(name = "fitchrating")
	private String fitchRating;
	 @Column(name = "ordernumber")
	private Integer orderNumber;
}
