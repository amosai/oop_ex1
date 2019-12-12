package Ex1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	public static final double EPSILON = 0.0000001;
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	Monom_Comperator compMonom= new Monom_Comperator();
	LinkedList<Monom> list_arr = new LinkedList();
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		compMonom= new Monom_Comperator();
		list_arr=new LinkedList();
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		s = s.replaceAll(" ", "");
		
		String [] a=s.split("(?=\\+|-)");
		for (int i = 0; i < a.length; i++) {
			this.add(new Monom(a[i]));
		}
		list_arr.sort(compMonom);
	}
	@Override
	public double f(double x) {
		double fx=0;
		

		for (int i = 0; i < list_arr.size(); i++) {


			fx += this.list_arr.get(i).f(x); 

		}
		return fx;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator <Monom> it1= p1.iteretor();

		while (it1.hasNext()) {

			this.add(it1.next());  
		}
	}

	@Override
	public void add(Monom m1) {
		int size= list_arr.size();

		if (size==0) {
			this.list_arr.add(new Monom(m1));//add of monom class
		}

		else {
			boolean add_hapend=false;
			for (int i = 0; i < size; i++)
			{
				//this.list_arr.add(m1);//add of monom class
				if(this.list_arr.get(i).get_power()==m1.get_power())//chek if we have in our polynom , 
				{                                                   // monoms with some ma'ala
					this.list_arr.get(i).add(m1);
					add_hapend=true;

				}

				if (add_hapend==true)
					i=size;//to get out from the "for loop"

			}
			if (add_hapend==false)//if we don't have in our polynom  monoms with some ma'ala we need 
				this.list_arr.add(new Monom(m1));//to add a new monom with that ma'ala
		}
		for (int i = 0; i < list_arr.size(); i++) {
			if (list_arr.get(i).get_coefficient()==0)
				list_arr.remove(i);
		}
		list_arr.sort(compMonom);
	}
// need fix!!!!!!!!!!!!!!!!!!!  subtract
	@Override
	public void substract(Polynom_able p1) {

		if(p1 instanceof  Polynom) {
			p1 = (Polynom)p1.copy();
		}
		
		Iterator <Monom> it1= p1.iteretor();
		Monom m2= new Monom("-1");
		Polynom p2=new  Polynom();
		while (it1.hasNext())
		{
			Monom m = new Monom(it1.next()); 
			m.substract();
			this.add(m);
			
		}
		list_arr.sort(compMonom);
	}

	
	
	@Override
	public void multiply(Polynom_able p1)
	{
		Polynom oldThis = new Polynom();
		oldThis.add(this);
		this.list_arr = new LinkedList();
		
		Iterator <Monom> it1= p1.iteretor();
		
		while (it1.hasNext()) 
		{
			
			Polynom_able tamp = oldThis.copy();
			tamp.multiply(it1.next());
			this.add(tamp);

		}		
		

	}

	
	
	@Override
	public boolean equals(Object p1) {
			if  (p1 instanceof Polynom) {
				
			
			Iterator <Monom> it1= ((Polynom) p1).iteretor();
			Iterator <Monom> it2= this.iteretor();
			while (it1.hasNext()&&it2.hasNext())			
			{
	
				if 	(it1.hasNext()&&!it2.hasNext()|| !it1.hasNext()&&it2.hasNext() )
					return false;
				if ( !it1.next().equals(it2.next()))
					return false;
			}
	
			return true;
		}
		return false;
	}

	@Override
	public boolean isZero() { // 
		if(this.list_arr.size() == 0)
			return true;
		return this.list_arr.get(0).isZero();
	}

	@Override
	public double root(double x0, double x1, double eps) {  // last last one
		double root1=0;

		if (Math.abs(f(x0))<0.0000001) {
			root1=x0;
			return root1;
		}
		if (Math.abs(f(x1))<0.0000001) {
			root1=x1;
			return root1;
		}

		if((this.f(x0)>0) &&(this.f(x1)>0)) {
			System.out.println("wrong numbers");
			return -1;
		}
		if((this.f(x0)<0) &&(this.f(x1)<0)) {
			System.out.println("wrong numbers");
			return -1;
		}

		double emza=1000,temp=0;
		if(x1<x0) 
		{
			temp=x1;
			x1=x0;
			x0=temp;
		}


		while(Math.abs(this.f(x0))>0.0001 && Math.abs(this.f(x1))>0.0001) {

			emza=x0+(Math.abs((x0-x1)/2));

			if ((this.f(x0)>0) && (this.f(emza)<0)) {
				x1=emza;

			}
			if ((this.f(x0)>0) && (this.f(emza)>0)) {
				x0=emza;

			}
			if (this.f(x1)>0 && this.f(emza)<0) {

				x0=emza;

			}
			if (this.f(x1)>0 && this.f(emza)>0)
			{
				x1=emza;

			}
			if (this.f(emza)==0 )
			{
				return emza;
			}
			if (this.f(x0)==0 )
			{
				return emza;

			}
		}return emza;
	}
	// TODO Auto-generated method stub



	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub

		Polynom p= new Polynom();

		p.add(this);

		return p;
	}

	@Override
	public Polynom_able derivative() { // lo meshne
		Polynom p= new Polynom();
		for (int i = 0; i < list_arr.size(); i++) {
			Monom m1= list_arr.get(i).derivative();	
			p.list_arr.add(m1);
		}		
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) { // last one
		double temp=0;
		if(x1<x0) 
		{
			temp=x1;
			x1=x0;
			x0=temp;
		}

		double area=0;
		double x_integral=x0;
		while(x_integral<x1)
		{
			if(this.f(x_integral)>0)
			{
			area=area+this.f(x_integral)*eps;
			}
			x_integral+=eps;
			
		}
	
	// TODO Auto-generated method stub
	return area;

	}
@Override
public Iterator <Monom> iteretor() {
	Iterator <Monom> it = list_arr.iterator();

	// TODO Auto-generated method stub
	return it;
}
@Override
public void multiply(Monom m1) {
	// TODO Auto-generated method stub
	int size=list_arr.size();
	for (int i = 0; i <size; i++) {
		this.list_arr.get(i).multipy(m1);

	}		

}

public String toString() {
	String ans="";

	for (int i = 0; i < list_arr.size(); i++) {
		if (this.list_arr.get(i).toString().charAt(i)=='+'||this.list_arr.get(i).toString().matches("[0-9].*"))
			ans+= "+";
		else
			ans+= "";
		ans+=this.list_arr.get(i).toString() + "";
	}
	return ans;
}
@Override
public function initFromString(String s) {
	Polynom fun= new Polynom(s);
	return fun;
}
}
