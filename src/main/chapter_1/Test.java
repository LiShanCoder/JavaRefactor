package main.chapter_1;

public class Test {

	public static void main(String[] args) {
		
		test1();
		/* 这次的重构到底有什么意义？
		 * 		1.对于少量代码的程序来说，任何修改可能都不合算。
		 * 		2.无论代码的多少，易读性的提升，都是有的
		 * 		3.对于应对后续相同模块的修改，有了质的提升（修改影片分类结构，改变费用计算规则，改变常客积分计算规则）
		 */
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
