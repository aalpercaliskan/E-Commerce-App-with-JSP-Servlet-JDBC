package com.godoro.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.godoro.database.entity.Category;
import com.godoro.database.entity.Product;
import com.godoro.database.manager.CategoryManager;

public class ProductXmlManager extends BaseXmlManager<Product>{

	@Override
	protected Document setEntityElement(Document document, Element element, Product product) {
		if(product != null) {
			element.setAttribute("productId", Long.toString(product.getProductId()));
			addSingleElementText(document, element, "productName", product.getProductName());
			addSingleElementText(document, element, "salesPrice", product.getSalesPrice());
			addSingleElementText(document, element, "categoryId", product.getCategory().getCategoryId());
		}
		return document;
	}

	@Override
	protected Product createEntityFromElement(Element element)  {
		Product product = null;
	
		if(element.hasAttribute("productId")) {
			long productId = getAttribute(element, "productId", 0);
			String productName = getSingleElementText(element, "productName","");
			double salesPrice =getSingleElementText(element, "salesPrice", .0);
			long categoryId = getSingleElementText(element, "categoryId", 0);
			 product = new Product(productId, productName, salesPrice);
			
			Category category = null;
			try {
				category = getCategory(categoryId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			product.setCategory(category);
		}
		
		
		return product;
	}
	
	private Category getCategory(long categoryId) throws Exception {
		CategoryManager categoryManager = new CategoryManager();
		Category category = categoryManager.find(categoryId);
		return category;
	}

}
