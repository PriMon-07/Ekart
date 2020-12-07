package com.infosys.ekart.service;

import java.util.List;

import com.infosys.ekart.exception.ProductNameAlreadyPresent;
import com.infosys.ekart.model.DealsModel;
import com.infosys.ekart.model.NotificationModel;
import com.infosys.ekart.model.PriceComparisonModel;
import com.infosys.ekart.model.Product;
import com.infosys.ekart.model.ProductDTO;
import com.infosys.ekart.model.ProductDetails;
import com.infosys.ekart.model.ProductDetailsDTO;
import com.infosys.ekart.model.RecommendationModel;
import com.infosys.ekart.model.Reviews;
import com.infosys.ekart.model.SellerDTO;

public interface ProductService {

	List<DealsModel> getDeals();
	List<PriceComparisonModel> priceComparison(String productName);
	//List<RecommendationModel> getRecommendations(String userId);
	void addProduct(ProductDTO productDTO) throws ProductNameAlreadyPresent;
	void searchByProductNameAndSellerId(String productName, String sellerId) throws ProductNameAlreadyPresent;
	void updatePriceAndDiscount(String sellerId,String displayName, Float price, Float discount);
	List<ProductDetailsDTO> getProductCatalog(String sellerId);
	List<Product> getProducts(String productName) throws Exception;
	ProductDetails getProductsByName(String productName, List<Reviews> reviews) throws Exception;
	public String modifyProduct(SellerDTO sellerDTO);
}
