package com.starstel.telcopro.stocks.restcontrollers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starstel.telcopro.stocks.entities.ProductCategory;
import com.starstel.telcopro.stocks.entities.SystemOS;
import com.starstel.telcopro.rh.services.EmployeeService;
import com.starstel.telcopro.stocks.entities.AppColor;
import com.starstel.telcopro.stocks.entities.Camera;
import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.MeasureUnit;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.MouvmentType;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.PortableCategory;
import com.starstel.telcopro.stocks.entities.PortableUnit;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.entities.Recipient;
import com.starstel.telcopro.stocks.entities.RecipientGroupe;
import com.starstel.telcopro.stocks.entities.State;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.services.AppColorService;
import com.starstel.telcopro.stocks.services.EntrepotService;
import com.starstel.telcopro.stocks.services.PortableService;
import com.starstel.telcopro.stocks.services.ProductService;
import com.starstel.telcopro.stocks.services.RecipientService;

@CrossOrigin("*")
@RestController
@RequestMapping("/stocks")
public class StockRestController 
{
	@Autowired
	private ProductService productService;
	@Autowired
	private AppColorService appColorService;

	@RequestMapping(value="",method=RequestMethod.GET)
	public List<Product> listProducts() {
		return productService.listProducts();
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Product getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}


	@RequestMapping(value="",method=RequestMethod.POST)
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public Boolean deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}

	@RequestMapping(value="/mouvments-of-product/{id}",method=RequestMethod.GET)
	public List<Mouvment> getAllMouvmentOfProduct(@PathVariable Long id) {
		return productService.getAllMouvmentOfProduct(id);
	}

	@RequestMapping(value="/mouvment-of-employee/{id}",method=RequestMethod.GET)
	public List<Mouvment> getAllMouvmentOfEmployee(@PathVariable Long id) {
		return productService.getAllMouvmentOfEmployee(id);
	}

	@RequestMapping(value="/app-colors",method=RequestMethod.POST)
	public AppColor saveAppColor(@RequestBody AppColor appColor) {
		return appColorService.saveAppColor(appColor);
	}

	@RequestMapping(value="/app-colors/{id}",method=RequestMethod.DELETE)
	public Boolean deleteAppColor(@PathVariable Long id) {
		return appColorService.deleteAppColor(id);
	}

	@RequestMapping(value="/app-colors",method=RequestMethod.GET)
	public List<AppColor> getAppColors() {
		return appColorService.getAppColors();
	}

	@RequestMapping(value="/app-colors/{id}",method=RequestMethod.GET)
	public AppColor getAppColor(@PathVariable Long id) {
		return appColorService.getAppColor(id);
	}

	@RequestMapping(value="/app-colors/search",method=RequestMethod.GET)
	public List<AppColor> searchAppColor(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return appColorService.search(keyWords);
	}

	@RequestMapping(value="/colors",method=RequestMethod.GET)
	public List<String> getColors() {
		return appColorService.getColors();
	}

	@RequestMapping(value="/poducts-color/{color}",method=RequestMethod.GET)
	public List<Product> getProductByColor(@PathVariable String color) {
		return appColorService.getProductByColor(color);
	}
	
	@RequestMapping(value="/states",method=RequestMethod.GET)
	public List<State> listStates() {
		return productService.listStates();
	}

	@RequestMapping(value="/states",method=RequestMethod.POST)
	public State saveState(@RequestBody State state) {
		return productService.saveState(state);
	}

	@RequestMapping(value="/states/{id}",method=RequestMethod.DELETE)
	public Boolean deleteState(@PathVariable Long id) {
		return productService.deleteState(id);
	}

	@RequestMapping(value="/states/{id}",method=RequestMethod.GET)
	public State getState(@PathVariable Long id) {
		return productService.getState(id);
	}

	@RequestMapping(value="/measure-units",method=RequestMethod.GET)
	public List<MeasureUnit> listMeasureUnits() {
		return productService.listMeasureUnits();
	}

	@RequestMapping(value="/measure-units",method=RequestMethod.POST)
	public MeasureUnit saveMeasureUnit(@RequestBody MeasureUnit measureUnit) {
		return productService.saveMeasureUnit(measureUnit);
	}

	@RequestMapping(value="/measure-units/{id}",method=RequestMethod.DELETE)
	public Boolean deleteMeasureUnit(@PathVariable Long id) {
		return productService.deleteMeasureUnit(id);
	}

	@RequestMapping(value="/measure-units/{id}",method=RequestMethod.GET)
	public MeasureUnit getMeasureUnit(@PathVariable Long id) {
		return productService.getMeasureUnit(id);
	}

	@RequestMapping(value="/categories",method=RequestMethod.GET)
	public List<ProductCategory> listProductCategorys() {
		return productService.listProductCategorys();
	}

	@RequestMapping(value="/categories",method=RequestMethod.POST)
	public ProductCategory saveProductCategory(@RequestBody ProductCategory productCategory) {
		return productService.saveProductCategory(productCategory);
	}

	@RequestMapping(value="/categories/{id}",method=RequestMethod.GET)
	public ProductCategory getProductCategory(@PathVariable Long id) {
		return productService.getProductCategory(id);
	}

	@RequestMapping(value="/categories/{id}",method=RequestMethod.DELETE)
	public Boolean deleteProductCategory(@PathVariable Long id) {
		return productService.deleteProductCategory(id);
	}

	@RequestMapping(value="/search",method=RequestMethod.GET)
	public List<Product> searchProducts(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return productService.searchProducts(keyWords);
	}

	@RequestMapping(value="/states/search",method=RequestMethod.GET)
	public List<State> searchStates(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return productService.searchStates(keyWords);
	}

	@RequestMapping(value="/categories/search",method=RequestMethod.GET)
	public List<ProductCategory> searchProductCategories(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return productService.searchProductCategories(keyWords);
	}

	@RequestMapping(value="/measure-units/search",method=RequestMethod.GET)
	public List<MeasureUnit> searchMeasureUnits(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return productService.searchMeasureUnits(keyWords);
	}
}
