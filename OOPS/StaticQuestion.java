public class StaticQuestion
{
    static {
        System.out.println("b");
    }
	public static void main(String[] args) {
		System.out.println("a");
		System.out.println(Class1.i);
	}
}

class Class1{
    static int i;
    
    static{
       System.out.println("c");
        i= 100;
    }
   
}
