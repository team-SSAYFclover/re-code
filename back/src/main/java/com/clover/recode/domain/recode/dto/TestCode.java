package com.clover.recode.domain.recode.dto;

public class TestCode {
    public static String prompt = """
            import java.util.Scanner;
                        
            public class Main {
                        
            	public static void main(String[] args) {
            		Scanner sc = new Scanner(System.in);
                        
            		int T = sc.nextInt();
            		
            		int[] abc = {300, 60, 10};
            		int[] abcC = new int[3];
            		for (int i = 0; i < 3; i++) {
            			abcC[i] = T/abc[i];
            			T %= abc[i];
            		}
            		if (T==0)
            			System.out.printf("%d %d %d", abcC[0], abcC[1], abcC[2]);
            		else
            			System.out.println("-1");
            	}
                        
            }
            """;
}
