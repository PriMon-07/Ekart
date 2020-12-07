package com.infosys.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.ekart.entity.CategoryRecommendationEntity;

public interface RecommendationRepository extends JpaRepository<CategoryRecommendationEntity, String>{

}
