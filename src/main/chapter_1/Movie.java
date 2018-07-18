package main.chapter_1;

public class Movie {
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;

	private String _title;
	private Price _price;
	
	public Movie(String _title, int _priceCode) {
		this._title = _title;
		setPriceCode(_priceCode);
	}

	public String getTitle() {
		return _title;
	}

	public int getPriceCode() {
		return _price.getPriceCode();
	}

	//初始化Movie.class时，根据对应priceCode创建Price.class子类
	public void setPriceCode(int _priceCode) {
		switch(_priceCode) {
		case REGULAR:
			_price = new RegularPrice();
			break;
		case NEW_RELEASE:
			_price = new NewReleasePrice();
			break;
		case CHILDRENS:
			_price = new ChildrensPrice();
			break;
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	//创建时已分类创建好Price.class子类，[运行时多态]直接调用对应的getCharge()方法
	double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}
	
	int getFrequentRenterPoints(int daysRented) {
		return _price.getFrequentRenterPoints(daysRented);
	}
	
}

abstract class Price{
	abstract int getPriceCode();
	abstract double getCharge(int daysRented);
	int getFrequentRenterPoints(int daysRented) {
		return 1;
	}
}

class RegularPrice extends Price{
	int getPriceCode() {
		return Movie.REGULAR;
	}
	double getCharge(int daysRented) {
		double result = 2;
		if(daysRented>2)
			result += (daysRented-2) * 1.5;
		return result;
	}
}

class NewReleasePrice extends Price{
	int getPriceCode() {
		return Movie.NEW_RELEASE;
	}
	double getCharge(int daysRented) {
		return daysRented * 3;
	}
	int getFrequentRenterPoints(int daysRented) {
		if(daysRented>1)
			return 2;
		else
			return 1;
	}
}

class ChildrensPrice extends Price{
	int getPriceCode() {
		return Movie.CHILDRENS;
	}
	double getCharge(int daysRented) {
		double result = 1.5;
		if(daysRented>3)
			result += (daysRented-3) * 1.5;
		return result;
	}
}