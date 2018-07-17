package main.chapter_1;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	private String _name;
	private Vector<Rental> _rentals = new Vector<Rental>();

	public Customer(String _name) {
		super();
		this._name = _name;
	}

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}
	
	public String getName() {
		return _name;
	}

	/**
	 * 生成订单信息 模块。
	 * 		经过多次重构之后，3个模块已经被分开。
	 * 		1.每个方法逻辑减少、代码量减少，易于阅读、维护
	 * 		2.逻辑分开后，易于重用。业务逻辑变动，只用在一处修改
	 * @return
	 * 			输出订单信息
	 */
	public String statement() {
		String result = "Rental Record for " + getName() + "\n";
		Enumeration<Rental> rentals = _rentals.elements();
		while(rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			result += "\t" + each.getMovie().getTitle() + "\t"+ String.valueOf( each.getCharge() ) + "\n";
		}
		
		result += "Amount owed is " + String.valueOf( getTotalCharge() ) + "\n";
		result += "You earned " + String.valueOf( getTotalFrequentRenterPoints() ) + " frequent renter points";
		return result;
	}
	
	/**
	 * 总费用计算 模块
	 * @return
	 */
	private double getTotalCharge() {
		double result = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		while(rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			result += each.getCharge();
		}
		return result;
	}
	
	/**
	 * 总积分计算 模块
	 * @return
	 */
	private int getTotalFrequentRenterPoints() {
		int result = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		while(rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}

}
