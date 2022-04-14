import java.util.*;
public class TypeCheck
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		Integer a = 1;
		Double m = 5.0;
		int x = 5;
		List<Integer> b = new ArrayList<>(Arrays.asList(2,3,4,5));
		List c = new ArrayList();
		List d = new ArrayList<>();
		d.add(3);
		d.add(new ArrayList<>(new ArrayList<>(Arrays.asList(7,8,9))) );
		System.out.println(a instanceof Integer);
// 		System.out.println(x instanceof Integer); // error : x:int not Integer
// 		System.out.println(a instanceof Double); // error  Integer cannot be converted to Double
// 		System.out.println(a instanceof Float); //error  Integer cannot be converted to Float
    	System.out.println(m instanceof Double);
    // 	System.out.println(m instanceof Integer); error
		System.out.println(b instanceof List);
		System.out.println(c instanceof List);
		System.out.println(d instanceof List);
	}
}
