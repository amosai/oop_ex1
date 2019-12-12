package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Polynom_able;
import junit.framework.TestCase;

class polynomTest extends TestCase {

	@Test
		void test() {
		ComplexFunction c1 = new ComplexFunction(Operation.Plus,new Polynom("3x^2"), new Polynom("8x^2"));
		assertEquals(11.0, c1.f(1));
			Polynom p1 = new Polynom();
			String[] monoms = {"1","x","x^2", "0.5x^2"};
			String s1 ="+1.5x^2+1.0x+1.0";
			String s2 ="+3.0x^2+2.0x+2.0";
			String s3 ="+0.5x^3+2.0x+2.0";
			for(int i=0;i<monoms.length;i++)
			{
			Monom m = new Monom(monoms[i]);
			p1.add(m);
			
			}
			
			assertEquals(s1, p1.toString());//check add(Monom)
			p1.add(p1);
			assertEquals(s2, p1.toString());//check add (Polynom)
			Polynom p2 = new Polynom(s2);// check  polynom(String s)
			Polynom p3 = new Polynom(s3);// check  polynom(String s)
			p3.substract(p2);//p3=+0.5x^3+2.0x+2.0//p2=+3.0x^2+2.0x+2.0
			String s4 ="+0.5x^3-3.0x^2";//p3=p3-p2=+0.5x^3-3.0x^2
			assertEquals(s4, p3.toString());//check substract(Polynom_able p1)
			String s5 ="+0.25x^6-3.0x^5+9.0x^4";
		p3.multiply(p3);//p3=+0.5x^3-3.0x^2
			assertEquals(s5, p3.toString());//check  multiply(Polynom_able p1)
		}//  i have problem with multiply(Polynom_able p1)
	@Test
	void testArea () {
		Polynom p4 = new Polynom("x");
		double eps =0.000001;
		double aa = p4.area(0, 1, eps);
		double expectedArea =0.5;
		     	if (Math.abs(expectedArea-aa)< eps*10)
						aa=expectedArea;

			assertEquals("is area: ", expectedArea, aa); // need to check how to test range
	System.out.println("area is : " +aa +"\nexpexted is : "+ expectedArea);
}
		@Test
void rootTest(){

}
public static void main (String[] args) {
	junit.textui.TestRunner.run(MonomTest.class);
}
	}
