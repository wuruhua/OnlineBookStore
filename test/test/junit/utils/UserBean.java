package test.junit.utils;

public class UserBean {
	private String name;
	private String introduction;

	public UserBean() {
		super();
	}

	public UserBean(String name, String introduction) {
		super();
		this.name = name;
		this.introduction = introduction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
