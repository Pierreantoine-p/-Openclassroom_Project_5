package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurveRepository extends JpaRepository<CurvePoint, Integer> {

}
