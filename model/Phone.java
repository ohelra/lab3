package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "phone") //lấy từ bảng DB
public class Phone {
	@Id
	@Column(name = "id")
	private String id;

	@NotNull
	@Column(name = "name")
	@Size(min = 3, max = 128)
	private String name;

	@NotNull
	@Column(name = "price")
	private int price;

	@NotNull
	@Column(name = "color")
	private String color;

	@NotNull
	@Column(name = "country")
	private String country;

	@Column(name = "quantity")
	private int quantity;

	public Phone() {
	}

	public Phone(String id, String name, int price, String color, String country, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.color = color;
		this.country = country;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", name=" + name + ", price=" + price + ", color=" + color + ", country=" + country
				+ ", quantity=" + quantity + "]";
	}
}
