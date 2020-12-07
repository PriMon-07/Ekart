package com.infosys.ekart.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infosys.ekart.entity.CategoryRecommendationEntity;
import com.infosys.ekart.entity.Deals;
import com.infosys.ekart.entity.ProductEntity;
import com.infosys.ekart.exception.ProductNameAlreadyPresent;
import com.infosys.ekart.model.BaseResponse;
import com.infosys.ekart.model.CartDTO;
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
import com.infosys.ekart.repository.DealsRepository;
import com.infosys.ekart.repository.ProductRepository;
import com.infosys.ekart.repository.RecommendationRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	DealsRepository dealsRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	RecommendationRepository recommendationRepository;
	@Autowired
	OrderFeignClient orderFeignClient;
	@Autowired
	ReviewFeignClient reviewFeignClient;
	@Autowired
	private Environment env;
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<DealsModel> getDeals() {
		
		 LocalDate now = LocalDate.now();  

		List<DealsModel> deals = new ArrayList<DealsModel>();
		List<Deals> dealsEntity= dealsRepository.findDealsByDate(now);
		for(Deals deal: dealsEntity) {
			deals.add(DealsModel.valueOf(deal));
		}
		return deals;
	}

	@Override
	public List<PriceComparisonModel> priceComparison(String productName) {
		List<ProductEntity> productList = productRepository.findByDisplayName(productName);
		List<PriceComparisonModel> priceComparisonList = new ArrayList<PriceComparisonModel>();

		for (ProductEntity product : productList) {
			PriceComparisonModel priceComparison = new PriceComparisonModel();
			priceComparison.setDeliveryCharge(product.getDeliveryCharge());
			priceComparison.setDiscount(product.getDiscount());
			priceComparison.setDisplayName(product.getDisplayName());
			float offerPrice = product.getPrice() - (product.getPrice() * product.getDiscount()) / 100;
			priceComparison.setOfferPrice(offerPrice);
			priceComparison.setPrice(product.getPrice());
			List<Integer> ratingList = reviewFeignClient.getRatings(productName);
			if(null != ratingList && !ratingList.isEmpty()) {
				float average = (float) ratingList.stream().mapToDouble(a ->a).average().getAsDouble();
				priceComparison.setRating(average);
			}else {
				priceComparison.setRating(0);
			}
			priceComparison.setSellerName(product.getSellerId());

			priceComparisonList.add(priceComparison);
		}

		return priceComparisonList;
	}

	/*
	@Override
	public List<RecommendationModel> getRecommendations(String userId) {
		List<RecommendationModel> recommendationList = new ArrayList<>();
		Map<String, RecommendationModel> recommendationMap = new HashMap<String, RecommendationModel>();
		Set<String> categoryList = orderFeignClient.getCategorySet(userId);

		if (null != categoryList && !categoryList.isEmpty()) {
			for (String category : categoryList) {
				Optional<CategoryRecommendationEntity> categoryRecommendation = recommendationRepository
						.findById(category);
				if (categoryRecommendation.isPresent()) {
					Set<String> recommendedCateories = categoryRecommendation.get().getCategoryRecommendationList();
					for (String sub : recommendedCateories) {
						List<ProductEntity> productList = productRepository.findByCategory(sub);
						for (ProductEntity product : productList) {
							if (!recommendationMap.containsKey(product.getDisplayName())) {
								RecommendationModel recommendedProduct = new RecommendationModel();
								recommendedProduct.setCategory(product.getCategory());
								recommendedProduct.setDisplayName(product.getDisplayName());
								recommendedProduct.setShortDesc(product.getShortDesc());
								recommendationMap.put(product.getDisplayName(), recommendedProduct);
							}
						}
					}
				}
			}
		}
		recommendationList.addAll(recommendationMap.values());
		return recommendationList;
	}
*/
	// Ashish US34,35
	public void addProduct(ProductDTO productDTO) throws ProductNameAlreadyPresent {
		searchByProductNameAndSellerId(productDTO.getDisplayName(), productDTO.getSellerId());
		ProductEntity product = productDTO.createEntity();
		productRepository.save(product);

	}

	// Ashish US34,35
	public void searchByProductNameAndSellerId(String productName, String sellerId) throws ProductNameAlreadyPresent {
		List<ProductEntity> productList = productRepository.findAllByDisplayNameAndSellerId(productName, sellerId);
		System.out.println(productList);
		if (productList.size() != 0) {
			throw new ProductNameAlreadyPresent(env.getProperty("ProductService.PRODUCT_ALREADY_PRESENT"));
		}
		// return productList;

	}

	// Ashish US34,35
	public void updatePriceAndDiscount(String sellerId, String displayName, Float price, Float discount) {
		productRepository.updatePriceAndDiscount(displayName, price, discount, sellerId);
	}

	// Ashish US34,35
	@HystrixCommand(fallbackMethod = "getProductCatalogFallback")
	public List<ProductDetailsDTO> getProductCatalog(String sellerId) {
		String OrderURI = "http://ORDERMS";
		// return restTemplate.exchange(OfferURI+"/offer/"+simId,HttpMethod.GET,entity,
		// SimOfferDTO.class).getBody();
		return restTemplate.exchange(OrderURI + "/getItemsSold/" + sellerId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ProductDetailsDTO>>() {
				}).getBody();

	}
	/*added by dhanapriya*/
	/*US36*/
	@Override
	public String modifyProduct(SellerDTO sellerDTO) {
		List<ProductEntity> prodList = productRepository.findAllByDisplayNameAndSellerId(sellerDTO.getProductName(), sellerDTO.getSellerName());
		String cartUri = "http://CartMS";
		try {
			for (ProductEntity productEntity : prodList) {
				/*productEntity.setPrice(sellerDTO.getPrice());
				productEntity.setDiscount(sellerDTO.getDiscount());
				productEntity.setDeliveryCharge(sellerDTO.getDeliveryCharge());
				productRepository.saveAndFlush(productEntity);*/
				updatePriceAndDiscount(productEntity.getSellerId(),productEntity.getDisplayName(),sellerDTO.getPrice(),sellerDTO.getDiscount());
				
			}
			List<CartDTO> cartList = restTemplate.getForObject(cartUri+"/searchcartseller/"+sellerDTO.getSellerName(), List.class); 
			NotificationModel notification = new NotificationModel();
			for (CartDTO cartDTO : cartList) {
				if(cartDTO.getDisplayName().equalsIgnoreCase(sellerDTO.getProductName())) {
					notification.setUserId(cartDTO.getUserId());
					notification.setMessage("Items in your cart has been provided a new price");
					notification.setMessageType("changes in cart");
					ResponseEntity<BaseResponse> response1 = restTemplate
							.postForEntity("http://NotificationMS/notifications/add", notification, BaseResponse.class);
				}
				
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			return "ModifyProduct.Failure";
		}
		return "ModifyProduct.Success";
	}
	/*---------------*/

	public List<ProductDetailsDTO> getProductCatalogFallback(String sellerId) {
		List<ProductDetailsDTO> list = new ArrayList<>();
		return list;
	}

	@Override
	public List<Product> getProducts(String name) throws Exception {
		List<ProductEntity> products = productRepository.findByDisplayNameLike(name + "%");
		if (products.size() == 0) {
			throw new Exception("NO_Product");
		}
		List<Product> listedProducts = new ArrayList<>();
		for (ProductEntity entity : products) {
			Product product = Product.toDTO(entity);
			listedProducts.add(product);
		}
		List<Product> finalProducts = this.getrefinedList(listedProducts);

		return finalProducts;
	}

	@Override
	public ProductDetails getProductsByName(String name, List<Reviews> reviews) throws Exception {
		List<ProductEntity> products = productRepository.findByDisplayName(name);
		if (products.size() == 0) {
			throw new Exception("NO_Product");
		}
		List<Product> listedProducts = new ArrayList<>();
		for (ProductEntity entity : products) {
			Product product = Product.toDTO(entity);
			listedProducts.add(product);
		}

		Double sum = 0.0;
		for (Reviews review : reviews) {
			sum += review.getRating();
		}
		ProductDetails productDetails = new ProductDetails();
		productDetails.setAvgRating(sum / reviews.size());
		productDetails.setCategory(listedProducts.get(0).getCategory());
		productDetails.setDeliveryCharge(listedProducts.get(0).getDeliveryCharge());
		productDetails.setDesc(listedProducts.get(0).getDescription());
		productDetails.setDiscount(listedProducts.get(0).getDiscount());
		productDetails.setDisplayName(listedProducts.get(0).getDisplayName());
		productDetails.setPrice(listedProducts.get(0).getPrice());
		productDetails.setReviews(reviews);
		productDetails.setSeller(listedProducts.get(0).getSellerId());
		productDetails.setSellerCount(listedProducts.size());
		productDetails.setShortDesc(listedProducts.get(0).getShortDesc());

		return productDetails;
	}

	public List<Product> getrefinedList(List<Product> listedProducts) {
		List<Product> finalProducts = new ArrayList<>();
		for (Product product : listedProducts) {
			Product compare = product;
			for (Product product2 : listedProducts) {
				if ((product2.getPrice() - product2.getDiscount()) <= (compare.getPrice() - compare.getDiscount())
						&& product2.getDisplayName().equals(compare.getDisplayName())) {
					compare = product2;
				}
			}
			Integer flag = 0;
			for (Product pd : finalProducts) {
				if (pd.getProductId() == compare.getProductId()) {
					flag = 1;
				}
			}
			if (flag == 0) {
				finalProducts.add(compare);
			}

		}

		return finalProducts;

	}

}
