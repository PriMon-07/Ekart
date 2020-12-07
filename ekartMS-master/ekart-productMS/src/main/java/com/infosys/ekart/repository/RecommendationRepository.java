package com.infosys.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ekart.entity.CategoryRecommendationEntity;

@Repository
public interface RecommendationRepository extends JpaRepository<CategoryRecommendationEntity, String>{

}
