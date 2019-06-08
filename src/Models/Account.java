package Models;

//Shecvlili.

public class Account {
	
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String birthDate;
	private String id;
	
	/**
	 * Creates new Account Object
	 * @param firstName 
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public Account(String firstName, String lastName, String email, String username, String password, String birthDate, String id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.id = id;
	}
	
	/**
	 * @return First name of the Account user.
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * @return Last name of the Account user.
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * @return Email of the Account user.
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * @return Username of the Account user;
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @return Password hash of the Account user.
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * @return Birth date of the account user.
	 */
	public String getBirthDate() {
		return this.birthDate;
	}
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * Updates accounts first name to the given string.
	 * @param
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Updates accounts last name to the given string. 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Updates accounts email to the given string.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Updates accounts username to given string.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Updates accounts password hash to the given string.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Updates accounts users birth date to given string.
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	/**
	 * Overrides toString method.
	 * Returns accounts info in string.
	 */
	public String toString() {
		String result = "";
		result += "first_name: " + this.firstName + "\n";
		result += "last_name: " + this.lastName + "\n";
		result += "email: " + this.email + "\n";
		result += "username: " + this.username + "\n";
		result += "passwordHash: " + this.password + "\n";
		result += "birth_date: " + this.birthDate;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) 
			return true;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account)obj;
		if(isSame(other)) 
			return true;
		else
			return false;
	}
	
	//Compares two Accounts info.
	private boolean isSame(Account other) {
		if(this.toString().equals(other.toString()))
			return true;
		else
			return false;
	}
}
