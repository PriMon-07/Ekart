package com.infy.ekart.controller;
@RestController
@RequestMapping
public class RestController {

	
	@Autowired
	private ProductService productService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = "/searchproduct/{productName}")
	public List<ProductDTO> getProducts(@PathVariable("productName") String productName){
		logger.info("------getting product details-----");
		return productService.findProductByName(productName);
	}
	@GetMapping(value = "/searchproductbysellerid/{sellerid}")
	public List<ProductDTO> getProductsBySellerId(@PathVariable("sellerid") Integer seller_id){
		logger.info("------getting product details-----");
		return productService.findBySellerId(seller_id);
	}
}

