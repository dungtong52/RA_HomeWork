import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.util.Optional;

public class Order {
	private int id;
	private String customerName;
	private LocalDate createDate;
	private Optional <LocalDate> deliveryDate;

	public Order () {
	}

	public Order (int id, String customerName, LocalDate createDate, Optional <LocalDate> deliveryDate) {
		this.id = id;
		this.customerName = customerName;
		this.createDate = createDate;
		this.deliveryDate = deliveryDate;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getCustomerName () {
		return customerName;
	}

	public void setCustomerName (String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getCreateDate () {
		return createDate;
	}

	public void setCreateDate (LocalDate createDate) {
		this.createDate = createDate;
	}

	public Optional <LocalDate> getDeliveryDate () {
		return deliveryDate;
	}

	public void setDeliveryDate (Optional <LocalDate> deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public String toString () {
		String delivery = deliveryDate.map(LocalDate::toString).orElse("Chưa giao");
		return String.format("ID: %d - Customer Name: %s - Order Date: %s - Deliver Date: %s",
				this.id, this.customerName, this.createDate, this.deliveryDate);
	}
}
