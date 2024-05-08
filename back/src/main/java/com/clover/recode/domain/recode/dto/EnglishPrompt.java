package com.clover.recode.domain.recode.dto;

public class EnglishPrompt {
    public static String prompt = """
You are to assist the user's learning of algorithms by marking various parts of the given code, excluding parts that you think are truly not meaningful for learning. The detailed rules are as follows:
- Except for parts that seem truly unimportant for learning, like the import statement, mark everything else.
- The way to mark is to insert the special character `‽` immediately before the start of each part of the string and insert the special character `▢` immediately after the end of each part of the string, implying that the part between the special characters `‽` and `▢` has been marked.
- The marked parts will be categorized into high, medium, and low importance and difficulty.
- Parts of low importance and difficulty will have one `‽` and `▢` at the beginning and end, respectively. Parts of medium importance and difficulty will have two `‽‽` and `▢▢` at the beginning and end, respectively. Parts of high importance and difficulty will have three `‽‽‽` and `▢▢▢` at the beginning and end, respectively.
- The code must contain at least one part of each difficulty level.
- That is, any code must have at least one part surrounded by the low-difficulty special characters `‽` and `▢`, one part surrounded by the medium-difficulty special characters `‽‽` and `▢▢`, and one part surrounded by the high-difficulty special characters `‽‽‽` and `▢▢▢` for a total of at least three parts.
- Never forget that the number of the character ‽ just before the start of the section to be marked and the character ▢ right after the end must be the same.
- You should not add any explanations other than the code in your response.
- Your response should consist only of the code and the special characters marking the important parts of the code. Do not engage in any other activities such as explaining the code.
            
Now, I will show you an example below.



The code given to you:

```
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
```



The code you can generate as a response from the given rules:

```
import java.io.BufferedReader;
import java.io.InputStreamReader;
            
public class Main {
    public static void main(String[] args) throws Exception {
        ‽BufferedReader▢ br = ‽‽new BufferedReader(new InputStreamReader(System.in));▢▢
        
        ‽int[]▢ arr = ‽new int[9];▢
        
        ‽int▢ sum = ‽0▢;
        ‽for▢ (‽int▢ i = ‽0▢; ‽i < 9;▢ ‽i++▢) {
            ‽‽arr[i]▢▢ = ‽‽Integer.parseInt(br.readLine());▢▢
            ‽‽sum▢▢ ‽+=▢ ‽arr[i]▢;
        }
        
        ‽mainloop▢: ‽for▢ (‽int▢ i = ‽0▢; ‽‽i < 8▢▢; ‽i++▢) {
            ‽for▢ (‽int▢ j = ‽‽i+1▢▢; ‽‽j < 9▢▢; ‽j++▢) {
                ‽‽if▢▢ (‽‽‽(sum-arr[i]-arr[j]) == 100▢▢▢) {
                    ‽for▢ (‽int▢ j2 = ‽0▢; ‽‽j2 < 9▢▢; ‽j2++▢) {
                        if (‽‽‽j2!=i&&j2!=j▢▢▢) {
                            ‽‽System.out.println(arr[j2])▢▢;
                        }
                    }
                    ‽‽‽break mainloop▢▢▢;
                }
            }
        }
    }
}
```


Additional example)
The code given to you:

```
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 17069 파이프 옮기기 2
public class Main {

    public static void main(String[] args) throws Exception {
        
        // 입력 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 크기 N 읽기
        int N = Integer.parseInt(br.readLine());
        
        // 맵 객체 생성
        int[][] map = new int[N][N];
        
        // 맵 읽기
        for (int i = 0; i < N; i++) {
            
            // 문자열 토큰화 객체 생성 후 한 줄 읽기
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            //
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
            
        }
        
        long[][][] dp = new long[N][N][3];
        dp[0][1][0] = 1;
        
        for (int i = 0; i < N; i++)
            for (int j = 2; j < N; j++) {
                if (map[i][j] == 1)
                    continue;
                
                if (j > 0)
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
                
                if (i > 0 && j > 0 && map[i-1][j] != 1 && map[i][j-1] != 1)
                    dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                
                if (i > 0)
                    dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
                
            }
        
        
        System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);
    }

}
```



The code you can generate as a response from the given rules:

```
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 17069 파이프 옮기기 2
public class Main {

    public static void main(String[] args) throws Exception {
            
        // 입력 객체 생성
        ‽BufferedReader▢ br = ‽‽new BufferedReader(new InputStreamReader(System.in));▢▢
            
        // 크기 N 읽기
        ‽int▢ N = ‽Integer.parseInt(br.readLine());▢
            
        // 맵 객체 생성
        ‽int[][]▢ map = ‽new int[N][N];▢
            
        // 맵 읽기
        ‽‽for (int i = 0; i < N; i++)▢▢ {
                
            // 문자열 토큰화 객체 생성 후 한 줄 읽기
            ‽StringTokenizer▢ st = ‽new StringTokenizer(br.readLine())▢;
                
            //
            ‽‽for (int j = 0; j < N; j++)▢▢
                ‽‽map[i][j]▢▢ = ‽Integer.parseInt(st.nextToken());▢
                
        }
            
        ‽‽long[][][]▢▢ dp = ‽‽new long[N][N][3];▢▢
        ‽‽dp[0][1][0]▢▢ = ‽‽1;▢▢
            
        ‽‽for (int i = 0; i < N; i++)▢▢
            ‽‽‽for (int j = 2; j < N; j++)▢▢▢ {
                ‽‽if (map[i][j] == 1)▢▢
                    ▢▢continue;▢▢
                    
                ‽‽if (j > 0)▢▢
                    ‽‽dp[i][j][0]▢▢ = ‽‽‽dp[i][j-1][0] + dp[i][j-1][1];▢▢▢
                    
                ‽‽‽if (i > 0 && j > 0 && map[i-1][j] != 1 && map[i][j-1] != 1)▢▢▢
                    ‽‽dp[i][j][1]▢▢ = ‽‽‽dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];▢▢▢
   
                ‽if (i > 0)▢
                    ‽dp[i][j][2]▢ = ‽‽dp[i-1][j][1] + dp[i-1][j][2];▢▢
            }
            
            
        ‽System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);▢
    }

}
```



Now I will give you a code below. Based on the rules and examples specified above, generate an appropriate response:

```
            """;
}
