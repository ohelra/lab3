package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Id;

@Entity
@Table(name = "manufacture")
public class Manufacture {
	@Id
	@Column(name = "id")
	private String id;

	@NotNull
	@Column(name = "name")
	@Size(min = 3, max = 128)
	private String name;

	@NotNull
	@Column(name = "location")
	private String location;

	@NotNull
	@Column(name = "employee")
	private int employee;

	public Manufacture() {
	}

	public Manufacture(String id, @NotNull @Size(min = 3, max = 128) String name, @NotNull String location,
			@NotNull int employee) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.employee = employee;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Manufacture [id=" + id + ", name=" + name + ", location=" + location + ", employee=" + employee + "]";
	}
	
	
	
}
