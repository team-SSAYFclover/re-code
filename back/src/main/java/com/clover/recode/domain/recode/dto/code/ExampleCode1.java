package com.clover.recode.domain.recode.dto.code;

public class ExampleCode1 {
    public static String code = """
import java.io.BufferedReader;
import java.io.InputStreamReader;
            
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[] arr = new int[9];
        
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }
        
        mainloop: for (int i = 0; i < 8; i++) {
            for (int j = i+1; j < 9; j++) {
                if ((sum-arr[i]-arr[j]) == 100) {
                    for (int j2 = 0; j2 < 9; j2++) {
                        if (j2!=i&&j2!=j) {
                            System.out.println(arr[j2]);
                        }
                    }
                    break mainloop;
                }
            }
        }
    }
}
            """;
}
