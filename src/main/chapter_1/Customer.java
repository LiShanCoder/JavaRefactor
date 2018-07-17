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
	 * 生成订单的方法。处理了3个模块：积分计算；总费用计算；输出订单信息；
	 * 		存在的问题：
	 * 		1.单个模块的新增功能，都需要复制所有代码重新编写
	 * 		2.因为复制整个代码块，在修改共有逻辑时，反而增加要修改多处
	 * @return
	 * 			输出订单信息
	 */
	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		String result = "Rental Record for " + getName() + "\n";
		while(rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();
			
			frequentRenterPoints++;
			
			if((each.getMovie().getPriceCode()==Movie.NEW_RELEASE) &&
					each.getDaysRented()>1)
				frequentRenterPoints++;
			
			result += "\t" + each.getMovie().getTitle()+"\t"+
					String.valueOf(each.getCharge())+"\n";
			totalAmount += each.getCharge();
		}
		
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) +
				" frequent renter points";
		return result;
	}

}
