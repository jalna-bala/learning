package testt;

public class logical {
public static void main(String[] args) {
	int a=20;
	int b=40;
	int c=0;
	a=a+b;
	b=a-b;
	a=a-b;
	System.out.println(a);//20+40=60
	System.out.println(b);//60-20=40
	//System.out.println(a);//40-60=-20
}
}
