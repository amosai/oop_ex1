package Ex1;

public class PolynomTest {
	public static void main(String[] args) {
//		test1();
//		test2();
		test3();
	}
	
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1x","1x^4","1x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
//		System.out.println(p1.toString());
		//double aa = p1.area(0, 1, 0.0001);
		//p1.substract(p1);
		System.out.println(p1.toString());
	}
	public static void test3() {
		Polynom p1= new Polynom("-x^6-20x^5");
		Monom m2= new Monom("2x^2");
		//System.out.println("m2="+m2);
		
		System.out.println("p1 is: " + p1.toString());
		//p1.add(m2);
		//System.out.println("p1=p1+m2: " + p1.toString());
		//System.out.println("p1="+ p1);
		//Polynom p2= new Polynom("-5x^4+20x^3");
		//System.out.println("p2="+p2);
		//p1.add(p2);
		//System.out.println("p1=p1+p2: "+p1);

		//p1.multiply(m2);
		//System.out.println("p1*m2: "+p1);
		//Polynom p3 = new Polynom("3x^2-6x^1");
//		System.out.println("p1 is : " + p1);
		//System.out.println("p3 is : " + p3);
//		System.out.println("m2="+m2);
		//Monom m4= new Monom("2x^2");
		//p3.multiply(m4);
		
		//System.out.println("2x^2*(3x^2-6x^1) = "+ p3);
		Polynom p4 = new Polynom("x");
		System.out.println("p4="+ p4);
		double eps=0.1;
		//System.out.println("="+p4.f(3.75));
		System.out.println("="+p4.root(0, 8, eps));
		double aa = p4.area(0, 1, 0.0001);
		System.out.println(p4.area(0.001, 1, 0.0001));
		
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		//Polynom_able pp1 = Polynom.parse(s1);
		//System.out.println("from string: "+pp1);
	}
}