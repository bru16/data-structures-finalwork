package conjuntistas;

public class TestAVL {

	public static void main(String[] args) {
        ArbolAVL a = new ArbolAVL();
        a.insertar(20);
        System.out.println(a.toString());
        a.insertar(19);
        System.out.println(a.toString());
        a.insertar(18);
        System.out.println(a.toString());
    }
	
}
