package Ex1;

import org.hamcrest.core.SubstringMatcher;
import org.junit.jupiter.engine.discovery.predicates.IsPotentialTestContainer;

public class ComplexFunction implements complex_function {
	Operation root;
	function right,left;
	public ComplexFunction(Operation root) {
		this.root = root;
	}
	public ComplexFunction(Operation root, function left,function right) {
		this.root = root;
		this.left = left;
		this.right = right;
	}
	public ComplexFunction( function f1) {
		this.root = Operation.None;
		this.left = f1;
		this.right=new Monom("0");
	}
	//	public ComplexFunction( String s) {
	//		
	//		function f = initFromString(s);
	//		
	//	}

	
	
	
	//public boolean equals(ComplexFunction c) {
	//	return this.root == c.root && this.left == c.left && this.right == c.right;
	//}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplexFunction other = (ComplexFunction) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (root != other.root)
			return false;
		return true;
	}
	
	
	@Override
	public double f(double x) { 
		double ans=0;
		if (this.root == Operation.Plus)
			ans=this.right.f(x)+this.left.f(x);
		 if (this.root == Operation.Times)
			ans=this.right.f(x)*this.left.f(x);
		if (this.root == Operation.Divid)
			ans=this.right.f(x)/this.left.f(x);
		if (this.root == Operation.Max)
			ans=Math.max(this.right.f(x),this.left.f(x)); 
		if (this.root == Operation.Min)
			ans=Math.min(this.right.f(x),this.left.f(x)); 
		if (this.root == Operation.None)
			;
		if (this.root == Operation.Error)
			throw new RuntimeException("ERR opertor	");
		if (this.root == Operation.Comp)
			this.left.f(this.right.f(x));



		return ans;



	} 

	@Override
	public String toString() {// לא ברור למה מחזיר נל
		String s= "" + this.root + "("  + this.left + "," + this.right  + ")";
		return s;
	}

	@Override
	public function initFromString(String s) {
		
		// בדיקת תקינות קלט- לבדוק שיש כמות סוגריים מאוזנים. לבדוק כמות פסיקים שווה לכמות סוגריים
		int indexPsik=indexPsik(s);
		if(!s.contains(","))
			return new Polynom(s);
		int cl=s.indexOf('(');
		int cr=s.lastIndexOf(')');

		function left=initFromString(s.substring(cl+1,indexPsik ));
		function right=initFromString(s.substring(indexPsik+1, cr));
		Operation opi=findOperation(s.substring(0, cl));

		return new ComplexFunction(opi, left, right);
	}

	private int indexPsik(String s) {
		int psik=0;
		int counter=-1;
		for (int i = 0; i < s.length(); i++) {
			char c =s.charAt(i);
			if (c=='(') 
				counter++;
			else if(c==')')
				counter--;
			else if (c==','&&counter==0) {
				psik=i;
				break;
			}


		}
		return psik;	
	}

	private Operation findOperation(String substring) {//לממש את כל הסוגי פעולות 
		if (substring.equals("Plus"))
			return Operation.Plus;
		if (substring.equals("Times"))
				return Operation.Times;
		if (substring.equals("Divid"))
				return Operation.Divid;
		if (substring.equals("Max"))
				return Operation.Max;
		if (substring.equals("Min"))
				return Operation.Min;
		if (substring.equals("Comp"))
			return Operation.Comp;
		if (substring.equals("None"))
			return Operation.None;
		if (substring.equals("Error"))
			return Operation.Error;
		return Operation.None;////לא בטוח בכלל שזה  נכון, צריךלחשוב מה צריך להחזיר 
										
	}
	@Override
	/*
	 *                                         max
	 *                          plus                          max
	 * 						                          22x^3      53
	 *                 min              plus
	 *            4x+2      8x^3      x^2       x^7
	 */   
	public function copy() {

		ComplexFunction c1 = new ComplexFunction( this.root, this.left.copy(),this.right.copy());


		return c1;
	}

	@Override
	public void plus(function f1) {
		function c =this.copy();
		this.root=Operation.Plus;
		this.left=c;
		this.right=f1;

	}

	@Override
	public void mul(function f1) {
		function c =this.copy();
		this.root=Operation.Times;
		this.left=c;
		this.right=f1;

		

	}

	@Override
	public void div(function f1) {
		function c =this.copy();
		this.root=Operation.Divid;
		this.left=c;
		this.right=f1;


	}

	@Override
	public void max(function f1) {
		function c =this.copy();
		this.root=Operation.Max;
		this.left=c;
		this.right=f1;


	}

	@Override
	public void min(function f1) {
		function c =this.copy();
		this.root=Operation.Min;
		this.left=c;
		this.right=f1;


	}

	@Override
	public void comp(function f1) {
		function c =this.copy();
		this.root=Operation.Comp;
		this.left=c;
		this.right=f1;


	}

	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.root;
	}

}
