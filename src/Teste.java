import java.io.IOException;

import javax.swing.JOptionPane;

public class Teste {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String a = JOptionPane.showInputDialog("digite ai corno");
		System.out.println(a);
		
		System.out.println(a.equals(""));
		
		for (int i = 0; i < 100; i++) {
			System.out.println(i);
		}
	}

}
