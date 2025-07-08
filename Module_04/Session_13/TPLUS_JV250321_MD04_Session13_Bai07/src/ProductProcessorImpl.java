import java.util.List;

public class ProductProcessorImpl implements IProductProcessor {

	@Override
	public double calculateTotalValue (List <Product> products) {
		return products.stream()
				.mapToDouble(Product::getPrice)
				.sum();
	}

	@Override
	public boolean hasExpensiveProduct (List <Product> products) {
		return IProductProcessor.super.hasExpensiveProduct(products);
	}
}
