package bean;

public class Contact {
	private String name;
	private String number;
	private boolean selected;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Contact(String name) {
		this.name = name;
		selected = false;
	}

	public Contact(String name2, String number2) {
		// TODO Auto-generated constructor stub
		name = name2;
		number = number2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
