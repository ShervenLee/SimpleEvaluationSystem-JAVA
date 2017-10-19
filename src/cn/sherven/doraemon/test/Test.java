package cn.sherven.doraemon.test;

public class Test {

	public static void main(String[] args) {
		int y = 3;
		for (int i = 0; i <= y; i++) {
			for (int j = 0; j < y - i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i+1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.exit(0);
	}

}
