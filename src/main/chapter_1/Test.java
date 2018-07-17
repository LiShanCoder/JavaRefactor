package main.chapter_1;

public class Test {

	public static void main(String[] args) {
//		String result = new Customer("小明").statement();
//		System.out.println(result);
		
		test1();
	}

	private static void test1() {
		Movie movie1 = new Movie("一类电影", Movie.REGULAR);
		Movie movie2 = new Movie("二类电影", Movie.NEW_RELEASE);
		Movie movie3 = new Movie("三类电影", Movie.CHILDRENS);
		
		Customer person = new Customer("小明");
		person.addRental(new Rental(movie1, 2));	//2
		person.addRental(new Rental(movie1, 3));	//3.5
		person.addRental(new Rental(movie2, 1));	//3
		person.addRental(new Rental(movie2, 2));	//6
		person.addRental(new Rental(movie3, 3));	//1.5
		person.addRental(new Rental(movie3, 4));	//3
		
		System.out.println( person.statement() );	
		//19	
		//7积分（租电影，1积分/1次；租NEW_RELEASE类电影，1积分/1次2天以上）
	}
	
}
