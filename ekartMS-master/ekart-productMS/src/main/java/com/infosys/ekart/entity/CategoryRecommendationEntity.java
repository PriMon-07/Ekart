package com.infosys.ekart.entity;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infosys.ekart.constants.ApplicationConstants;

@Entity
@Table(name = "category_recommendation")
public class CategoryRecommendationEntity {
	@Id
	String category;
	String categoryRecommendation;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryRecommendation() {
		return categoryRecommendation;
	}

	public void setCategoryRecommendation(String categoryRecommendation) {
		this.categoryRecommendation = categoryRecommendation;
	}

	public Set<String> getCategoryRecommendationList() {
		if (null != categoryRecommendation) {
			return new TreeSet<>(Arrays.asList(categoryRecommendation.split(ApplicationConstants.COMMA)));
		} else {
			return new TreeSet<>();
		}
	}
}
