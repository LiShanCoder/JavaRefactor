package main.chapter_1;

public class Movie {
	public static final int CHILDRENS = 2;
	public static final int NEW_RELEASE = 1;
	public static final int REGULAR = 0;

	private String _title;
	private Price _price;
	
	public Movie(String _title, int _priceCode) {
		this._title = _title;
		setPriceCode(_priceCode);			//Self Encapsulate Field，确保任何时候，都通过取值函数、设值函数来访问类型代码
	}

	public String getTitle() {
		return _title;
	}

	public int getPriceCode() {
		return _price.getPriceCode();
	}

	public void setPriceCode(int _priceCode) {
		switch(_priceCode) {
		case REGULAR:
			_price = new RegularPrice();
			break;
		case CHILDRENS:
			_price = new ChildrensPrice();
			break;
		case NEW_RELEASE:
			_price = new NewReleasePrice();
			break;
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	/*
	 * 重构难点：分离 变化的、不变化的
	 */
	double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}
	
	int getFrequentRenterPoints(int daysRented) {
		if((getPriceCode()==Movie.NEW_RELEASE) && daysRented>1)
			return 2;
		else
			return 1;
	}
	
}

abstract class Price{
	abstract int getPriceCode();

	double getCharge(int daysRented) {
		double result = 0;
		switch(getPriceCode()) {
		case Movie.REGULAR:
			result += 2;
			if(daysRented>2)
				result += (daysRented-2) * 1.5;
			break;
		case Movie.NEW_RELEASE:
			result += daysRented * 3;
			break;
		case Movie.CHILDRENS:
			result += 1.5;
			if(daysRented>3)
				result += (daysRented-3) * 1.5;
			break;
		}
		return result;
	}
}

class ChildrensPrice extends Price{
	@Override
	int getPriceCode() {
		return Movie.CHILDRENS;
	}
}

class NewReleasePrice extends Price{
	@Override
	int getPriceCode() {
		return Movie.NEW_RELEASE;
	}
}

class RegularPrice extends Price{
	@Override
	int getPriceCode() {
		return Movie.REGULAR;
	}
}