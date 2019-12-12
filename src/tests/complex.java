package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Operation;
import Ex1.Polynom;

class complex {

	@Test
	void test() {
		ComplexFunction c1 = new ComplexFunction(Operation.Plus, new Polynom("3x^2"), new Polynom("8x^2"));
		assertEquals(11, c1.f(1));
	}

}
