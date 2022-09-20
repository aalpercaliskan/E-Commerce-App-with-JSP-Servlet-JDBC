package com.godoro.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.godoro.database.entity.Category;

public class CategoryXmlManager extends BaseXmlManager<Category> {
	@Override
	protected Document setEntityElement(Document document, Element element, Category category) {
		if(category != null) {
			element.setAttribute("categoryId", Long.toString(category.getCategoryId()));
			addSingleElementText(document, element, "categoryName", category.getCategoryName());
		}
		return document;
	}

	@Override
	protected Category createEntityFromElement(Element element) {
		Category category = null;
		
		if(element.hasAttribute("categoryId")) {
			long categoryId = getAttribute(element, "categoryId", 0);
			String categoryName = getSingleElementText(element, "categoryName","");
			category = new Category(categoryId, categoryName);
		}
		return category;
	}

}
