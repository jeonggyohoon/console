package womanClothe;
// DTO는 저장소이다. 데이터를 가져오기도하지만 보내줄수도있음. 
public class WomanDTO {
	// private 사용하는 이유 : 데이터를 은닉화하기 위해 사용함(보안)
	private String clothNum = null; // 상품번호
	private String clothName = null; // 상품명
	private int price = 0; // 가격
	private String clothSize = null; // 의류 사이즈
	private String clothType = null;
	private String clothType2 = null;

	// 생성자 선언 방법 : public 클래스(매개변수){
	
	// }
	public WomanDTO(){
		
	}

	public WomanDTO(String clothNum, String clothName, int price, String clothSize, String clothType,
			String clothType2) {
		super();
		this.clothNum = clothNum;
		this.clothName = clothName;
		this.price = price;
		this.clothSize = clothSize;
		this.clothType = clothType;
		this.clothType2 = clothType2;
	}
	// Source > Generate Getters and Setters 
	// get : get은 set과달리 return값이 있다. 값을 반환하기 위해서~
	public String getClothNum() {
		return clothNum;
	}

	// set : 매개변수를 입력받는 기능이기때문에 get과 달리 return값이 없다. 
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

	// toString : 입력받은 값이 잘 들어왔는지 확인하기위해 사용한다.
	@Override
	public String toString() {
		return "WomanDTO [clothNum=" + clothNum + ", clothName=" + clothName + ", price=" + price + ", clothSize="
				+ clothSize + ", clothType=" + clothType + ", clothType2=" + clothType2 + "]";
	}

}
