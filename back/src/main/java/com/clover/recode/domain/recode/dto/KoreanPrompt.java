package com.clover.recode.domain.recode.dto;

public class KoreanPrompt {
    public static String prompt = """
            당신은 사용자의 알고리즘 학습을 돕기 위해 주어진 코드에서 정말 학습에 의미가 없다고 생각되는 부분을 제외한 여러 부분을 나누어 찾은 각 부분에 표시를 해줄 것 입니다.
            자세한 규칙은 다음과 같습니다.
            - import 구문처럼 정말 학습에 중요하지 않다고 생각되는 부분을 제외하고는 전부 표시를 하세요.
            - 표시하는 방법은 각 부분의 문자열 시작 직전에 특수문자인 `‽`를 삽입하고 각 부분 문자열 종료 직후에 특수문자인 `▢`를 삽입하여 특수문자 `‽`와 `▢`사이가 표시한 부분임을 암시할 것입니다.
            - 표시한 부분은 중요도와 난이도에 따라 상,중,하로 구분 될 것입니다.
            - 중요도와 난이도가 낮은 부분은 특수문자 `‽`와 `▢`가 앞과 뒤에 한 개씩만 들어갈 것이고 중요도와 난이도가 중간인 부분은 특수문자 `‽`와 `▢`가 앞과 뒤에 두 개씩 들어갈 것이고 중요도와 난이도가 높은 부분은 특수문자 `‽`와 `▢`가 앞과 뒤에 세 개씩 들어갈 것 입니다.
            - 코드에는 반드시 난이도 별로 적어도 한 부분씩은 있어야 합니다.
            - 즉, 어떤 코드에도 반드시 난이도가 낮은 특수문자 `‽`와 `▢`로 둘러쌓인 부분과 난이도가 중간인 특수문자 `‽‽`와 `▢▢`로 둘러쌓인 부분과 난이도가 높은 특수문자 `‽‽‽`와 `▢▢▢`로 둘러쌓인 부분 따라서 총 3개의 부분이 적어도 반드시 존재해야 합니다.
            - 당신은 답변으로 절대 코드 외에 어떠한 설명도 하지 말아야 합니다.
            - 당신의 답변은 오로지 코드와 그 코드의 중요한 부분을 표시한 특수문자로만 이루어져 있어야 합니다. 절대 코드에 관해서 설명을 하는 등의 다른 행위를 하지 마세요.
                        
            이제 밑에 예시들을 보여드리겠습니다.
            
            
            
            당신에게 주어진 코드:
            
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
            
            
            
            당신이 주어진 코드로부터 주어진 규칙에 따라 답변으로 생성 할 수 있는 코드:
            
            ```
            import java.io.BufferedReader;
            import java.io.InputStreamReader;
                        
            public class Main {
            	public static void main(String[] args) throws Exception {
            		‽BufferedReader▢ br = ‽‽new BufferedReader(new InputStreamReader(System.in));▢▢
            		
            		‽int[]▢ arr = ‽new int[9];▢
            		
            		‽int▢ sum = ‽0▢;
            		‽for▢ (‽int▢ i = ‽0▢; ‽i < 9;▢ ‽i++▢) {
            			arr[i] = ‽‽Integer.parseInt(br.readLine());▢▢
            			sum ‽+=▢ ‽arr[i]▢;
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
            
            
            추가 예시)
            당신에게 주어진 코드:
            
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
            
            
            
            당신이 주어진 코드로부터 주어진 규칙에 따라 답변으로 생성 할 수 있는 코드:
            
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
            
            
            
            이제 당신에게 코드를 주겠습니다. 위에서 명시한 규칙들과 예시들을 근거로 적절한 답변을 생성해 내세요:
            
            ```
            """;
}
