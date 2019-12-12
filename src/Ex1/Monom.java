
package Ex1;

import java.util.Comparator;



/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p =get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s)
	{        //string to monom
		s = s.replaceAll(" ", "");
		
		double a=0; 
		int b=0; // a is coefficient and b is power
		boolean flag_minus=false;
		String s1=s.toLowerCase();
		if (checkX(s1)!=true)// counter x if have more then 1
			throw new RuntimeException("you have more x needed");

		String [] arr=s1.split("x");
		//need to add X, -x and empty arr
		if (arr.length==0) {//   "x" case
			String	[] arr1=  {"1","^1"};
			arr=arr1;

		}
		if (s1.charAt(0)=='x') {//   " x^int " case
			String	[] arr1=  {"1",arr[1]};
			arr=arr1;
			
		}

		if (arr[0].charAt(0)=='-') { // if have minus in the first char

			flag_minus=true;
			if (s1.charAt(1)=='x') {
				String	[] arr1=  {"1","1"};
				arr=arr1;
			}
			else {
				arr[0]=arr[0].substring(1);

			}
		}
			if (arr[0].charAt(0)=='+')  // if have plus in the first char
			{
				arr[0]=arr[0].substring(1);
			}
			if (isNumber(arr[0])) {//check the coffi
				a=Double.parseDouble(arr[0]);
				if (flag_minus==true)
					a=a*(-1);
			}
			//  from here we find the power

			if (arr.length>1) {
				arr[1]=arr[1].replace("^", "");//
				try {
					b=Integer.parseInt(arr[1]);
				}
				catch (Exception e) {
		
					System.err.println("you can only numbers");	
					e.printStackTrace();
				}

			}
			if (arr.length==1) {
				if (s.contains("x"))
					b=1;
			}
		

			set_coefficient(a);
			set_power(b);
	
		}

		public void add(Monom m) {
			if (this._power== m._power) {
				this._coefficient+=m._coefficient;
			}
		}

		public void multipy(Monom d) {
			this._coefficient=d._coefficient*this._coefficient;
			this._power=this._power+d._power;
		}

		public String toString() {
			String ans="";
			if (_coefficient==0)
				return "0";
			if (_power==0)
				return ""+_coefficient;
			if (_power==1)
				return ""+_coefficient+"x";
			ans=""+_coefficient+"x^"+_power;
			return ans;


		}
		// you may (always) add other methods.

		//****************** Private Methods and Data *****************
		//*help function for Monon String** 
		/*private static boolean isNumber(String str) { 
		try {  
			Double.parseDouble(str);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		}  
	}*/

		private static boolean isNumber(String str) { 
			try {  
				Double.parseDouble(str);  
				return true;
			} catch(NumberFormatException e){  
				return false;  
			}  
		}
		private static boolean checkX(String s1)  {
			int i=0,counter_x=0;

			while (i<s1.length()&&counter_x<2) {

				if ((s1.charAt(i))==('x'))
					counter_x++;

				i++;
			}

			if (counter_x>=2)
				return false;
			return true;
		}

		public boolean equals(Monom m) {
			if(Math.abs(m.get_coefficient()-this.get_coefficient())<EPSILON&& this._power==m._power)
				return true;
			return false;


		}
		public void substract () {
			this._coefficient*=-1;
		}

		//*** end my code
		private void set_coefficient(double a){
			this._coefficient = a;
		}
		private void set_power(int p) {
			if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
			this._power = p;
		}
		private static Monom getNewZeroMonom() {return new Monom(ZERO);}
		private double _coefficient; 
		private int _power;
		@Override
		public function initFromString(String s) {
			Monom fun= new Monom(s);
			
			return fun;
		}
		@Override
		public function copy() {
			Monom fun= new Monom(this);
			 
			return fun;
		}


	}
