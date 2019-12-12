package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.complex_function;
import Ex1.function;

class ComplexFunctionTest {
	
	@Test
	void test() {

ComplexFunction c1 = new ComplexFunction(Operation.Plus, new Polynom("3x^2"), new Polynom("8x^2"));
		
		assertEquals(11.0, c1.f(1));
		ComplexFunction c2 = new ComplexFunction(Operation.Plus,    c1 , new Polynom("8x^2"));
		assertEquals(19.0, c2.f(1));
		
		ComplexFunction c3 = new ComplexFunction(Operation.Plus, new Polynom("3x^2"), new Polynom("8x^2"));
	function c4 = c2.copy();
		
		assertEquals(c4, c2);
		//c4.initFromString("Plus(x^2+5,comp (x^2,x)");
		ComplexFunction c5 = new ComplexFunction(Operation.Comp, new Polynom("x^2"), new Polynom("x"));
		ComplexFunction c6 = new ComplexFunction(Operation.Plus, new Polynom("x^2+5"), c5);
	//	assertEquals(c4, c6);

	}
	public static void main (String[] args) {/// לא ברור מה קורה כאן עם הבדיקה
		ComplexFunction stam1 = new ComplexFunction(Operation.Plus, new Polynom("3x^2"), new Polynom("8x^2"));
		System.out.println(stam1.initFromString("Plus(3x^2,8x^2)"));
		//function f = stam.initFromString("plus(plus(x^2+5,x)   ,x)");
		function f = stam1.initFromString("Plus(x^2+5,Comp (x^2,x))");
		
		System.out.println(stam1.toString());
		
		ComplexFunction stam2 = new ComplexFunction(Operation.Plus, new Polynom("x^2"), new Polynom("x^2"));
		System.out.println(stam2.toString());
	  stam1.plus(stam2);
	  System.out.println(stam1.toString());
	}
}
