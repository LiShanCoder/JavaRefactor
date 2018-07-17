package main.chapter_1;

public class Rental {
	private Movie _movie;
	private int _daysRented;

	public Rental(Movie _movie, int _daysRented) {
		super();
		this._movie = _movie;
		this._daysRented = _daysRented;
	}

	public Movie getMovie() {
		return _movie;
	}

	public int getDaysRented() {
		return _daysRented;
	}

	/* 最好不要在 另一个对象的属性基础上，使用switch语句。如果不得不使用，应该使用自己对象的数据。
	 * 这暗示，getCharge()应该移动到Movie.class中
	 */
	double getCharge() {
		return _movie.getCharge(_daysRented);
	}
	
	int getFrequentRenterPoints() {
		return _movie.getFrequentRenterPoints(_daysRented);
	}
}
