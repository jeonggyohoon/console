package manClothe;

public class musinsaDTO {

	private String clothNum = null; // 상품번호
	private String clothName = null; // 상품명
	private int price = 0; // 가격

	private String clothSize = null; // 의류 사이즈
	private String clothType = null;
	private String clothType2 = null;

	public musinsaDTO() {
		// TODO Auto-generated constructor stub
	}

	public musinsaDTO(String clothNum, String clothName, int price, String clothSize, String clothType,
			String clothType2) {
		super();
		this.clothNum = clothNum;
		this.clothName = clothName;
		this.price = price;
		this.clothSize = clothSize;
		this.clothType = clothType;
		this.clothType2 = clothType2;
	}

	@Override
	public String toString() {
		return "musinsaDTO [clothNum=" + clothNum + ", clothName=" + clothName + ", price=" + price + ", clothSize="
				+ clothSize + ", clothType=" + clothType + ", clothType2=" + clothType2 + "]";
	}

	public String getClothNum() {
		return clothNum;
	}

	public void setClothNum(String clothNum) {
		this.clothNum = clothNum;
	}

	public String getClothName() {
		return clothName;
	}

	public void setClothName(String clothName) {
		this.clothName = clothName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getClothSize() {
		return clothSize;
	}

	public void setClothSize(String clothSize) {
		this.clothSize = clothSize;
	}

	public String getClothType() {
		return clothType;
	}

	public void setClothType(String clothType) {
		this.clothType = clothType;
	}

	public String getClothType2() {
		return clothType2;
	}

	public void setClothType2(String clothType2) {
		this.clothType2 = clothType2;
	}

}
