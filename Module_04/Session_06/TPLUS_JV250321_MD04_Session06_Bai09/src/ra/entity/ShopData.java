package ra.entity;

import ra.entity.Product;
import ra.entity.Categories;

public class ShopData {
	public Categories[] categoryList = new Categories[100];
	public Product[] productList = new Product[100];
	public int currentCate = 0;
	public int currentProd = 0;
}
