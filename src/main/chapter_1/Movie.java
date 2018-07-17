package main.chapter_1;

public class Movie {
	public static final int CHILDRENS = 2;
	public static final int NEW_RELEASE = 1;
	public static final int REGULAR = 0;

	private String _title;
	private int _priceCode;

	public Movie(String _title, int _priceCode) {
		super();
		this._title = _title;
		this._priceCode = _priceCode;
	}

	public String getTitle() {
		return _title;
	}

	public int getPriceCode() {
		return _priceCode;
	}

	public void setPriceCode(int _priceCode) {
		this._priceCode = _priceCode;
	}

	/* 参数的选择上：选择[租期长度]作为参数，而不使用[影片类型]作为参数？
	 * 		[租期长度]与Movie.class变动关系不大，置于Movie.class外部，以参数形式传入
	 * 		[影片类型]与Movie.class关系密切，应置于Movie.class内部，与[影片类型]相关的操作都应该放在Movie.class中
	 */
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
	
	int getFrequentRenterPoints(int daysRented) {
		if((getPriceCode()==Movie.NEW_RELEASE) && daysRented>1)
			return 2;
		else
			return 1;
	}
	
}
