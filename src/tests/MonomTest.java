package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;
import junit.framework.TestCase;

public class MonomTest extends TestCase {


	public void test1() {// chek monom and to string		
		///////check ComplexFunction
//		ComplexFunction c1 = new ComplexFunction(Operation.Plus, new Polynom("3x^2"), new Polynom("8x^2"));
//		
//		assertEquals(11.0, c1.f(1));
//		ComplexFunction c2 = new ComplexFunction(Operation.Min,    c1 , new Polynom("8x^2"));
//		assertEquals(19, c1.f(1));
//		
//		ComplexFunction c3 = new ComplexFunction(Operation.Plus, new Polynom("3x^2"), new Polynom("8x^2"));
//		function c4 = c2.copy();
//		
//		assertEquals(c4, c2);
//		c4.initFromString("plus(x^2+5,comp (x^2,x)");
//		
		
		

		
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"3x^2","-x^1","-3.2x^2","x","0"};
		String[] monomExpected = {"3.0x^2","-1.0x","-3.2x^2","1.0x","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			String sEquals= monomExpected[i];
			assertEquals(s, sEquals);
		}
	}

	@Test
	public static void test2() {//check monom and add
	System.out.println("*****  Test2:  *****");
	ArrayList<Monom> monoms = new ArrayList<Monom>();
	monoms.add(new Monom(0,5));
	monoms.add(new Monom(-1,0));
	monoms.add(new Monom(-1.3,1));
	monoms.add(new Monom(-2.2,2));
	String[] monomExpected = {"0","-1.0","-1.3x","-2.2x^2"};
	for(int i=0;i<monoms.size();i++) {
		Monom m = new Monom(monoms.get(i));
		String s = m.toString();
		
		String s1Equals= monomExpected[i];
		assertEquals(s, s1Equals);
	}
	}
	@Test
	public static void test3() {//check monom zero and f(x)****** need to do f(x) test
	System.out.println("*****  Test3:  *****");
	String[] monoms = {"3","-x^1","-3.2x^2","x", "0"};
	double  [] 	fxExpected= {3,-1,-12.8,3,0};
	boolean [] isZeroExpected = {false,false,false,false,true};
//	System.out.println((i+1)+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
	for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[i]);
		assertEquals(isZeroExpected[i], m.isZero());
	
		double fx = m.f(i);
		assertEquals(fxExpected[i],fx );
		Monom M = new Monom("-3.2x^");
		Monom L = new Monom("-x");
		M.multipy(L);
		Monom ML = new Monom("3.2x^3");
		assertEquals(ML, M );
		
	}
	
	}
	
	
	
	public static void main (String[] args) {/// לא ברור מה קורה כאן עם הבדיקה
//		ComplexFunction stam1 = new ComplexFunction(Operation.Plus, new Polynom("3x^2"), new Polynom("8x^2"));
//		System.out.println(stam1.initFromString("Plus(3x^2,8x^2)"));
//		//function f = stam.initFromString("plus(plus(x^2+5,x)   ,x)");
//		function f = stam1.initFromString("Plus(x^2+5,Comp(x^2,x))");
//		
//		System.out.println(stam1.toString());
//		
//		ComplexFunction stam2 = new ComplexFunction(Operation.Plus, new Polynom("x"), new Polynom("x"));
//		System.out.println(stam2.toString());
//		 System.out.println(stam1.f(1));
//	  stam1.mul(stam2);
//	  System.out.println(stam1.toString());
//	  System.out.println(stam1.f(1));
		
	}
}
