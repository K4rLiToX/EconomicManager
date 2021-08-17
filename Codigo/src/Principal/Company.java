package Principal;


public class Company {
	//Variables
	private String Name;
	private String phoneNumber;
	private String Email;
	private String Address;
	
	public Company(String name, String phoneNumber, String email, String address) {
		super();
		Name = name;
		this.phoneNumber = phoneNumber;
		Email = email;
		Address = address;
	}
	
	//Getters And Setters
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public String dumpCompany() {
		return "CMP>>"+Name+">>"+phoneNumber+">>"+Email+">>"+Address;
	}
}
