
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Operation;
import Ex1.Range;
import Ex1.function;

class Functions_GUItest {

	@Test
	void test1() {
		ComplexFunction stam= new ComplexFunction(Operation.None);//do that beacose initFromString not static
		function f0 = stam.initFromString("Plus(x^2+5,Comp(x^2,x))");
		function f1 = stam.initFromString("x^2");
		function f2 = stam.initFromString("8x^2");
		Functions_GUI gui = new Functions_GUI(); 
		try {
			gui.initFromFile("function_example.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		assertEquals(f0, gui.get(0));
		assertEquals(f1, gui.get(1));
		assertEquals(f2, gui.get(2));
		
		
	}
	
	@Test
	void test2() {
		String nameFile = "test.txt";
		
		Functions_GUI gui1 = new Functions_GUI();
		ComplexFunction stam= new ComplexFunction(Operation.None);//do that beacose initFromString not static
		function f0 = stam.initFromString("Plus(x^2+5,Comp(x^2,x))");
		function f1 = stam.initFromString("x^2");
		function f2 = stam.initFromString("8x^2");
		
		gui1.add(f0);
		gui1.add(f1);
		gui1.add(f2);
		
		try {
			gui1.saveToFile(nameFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//test i can reaf the file
		Functions_GUI gui2 = new Functions_GUI();
		try {
			gui2.initFromFile(nameFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(gui1, gui2);
	}
	

	
	public static void main(String[] args) {
		Functions_GUI gui = new Functions_GUI();
		ComplexFunction stam= new ComplexFunction(Operation.None);//do that beacose initFromString not static
		function f0 = stam.initFromString("Plus(x^2+5,Comp(x^2,x))");
		function f1 = stam.initFromString("x^2");
		function f2 = stam.initFromString("8x^2");
		gui.add(f0);
		gui.add(f1);
		gui.add(f2);
		//gui.drawFunctions(700, 500, new Range(-4,4), new Range(-4,4),8);
		gui.drawFunctions("GUI_params.txt");
		
		
		
	}

}
