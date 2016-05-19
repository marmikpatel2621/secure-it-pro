package bean;

public class ApplockAppList {

	private String name;
	private boolean selected;
	int img;

	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}

	public ApplockAppList(String name) {
		this.name = name;
		selected = false;
		img = 0;
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
