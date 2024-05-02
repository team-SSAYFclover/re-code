package com.clover.recode.domain.recode.dto;

public class EnglishPrompt {
    public static String prompt = """
            You are to assist the user's learning of algorithms by marking various parts of the given code, excluding parts that you think are truly not meaningful for learning. The detailed rules are as follows:
            - Mark all parts except those you think are truly not important for learning.
            - The way to mark is to insert the special character `‽` immediately before the start of each part of the string and insert the special character `▢` immediately after the end of each part of the string, implying that the part between the special characters `‽` and `▢` has been marked.
            - The marked parts will be categorized into high, medium, and low importance and difficulty.
            - Parts of low importance and difficulty will have one `‽` and `▢` at the beginning and end, respectively. Parts of medium importance and difficulty will have two `‽‽` and `▢▢` at the beginning and end, respectively. Parts of high importance and difficulty will have three `‽‽‽` and `▢▢▢` at the beginning and end, respectively.
            - The code must contain at least one part of each difficulty level.
            - That is, any code must have at least one part surrounded by the low-difficulty special characters `‽` and `▢`, one part surrounded by the medium-difficulty special characters `‽‽` and `▢▢`, and one part surrounded by the high-difficulty special characters `‽‽‽` and `▢▢▢` for a total of at least three parts.
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
                        
            Now I will give you a code below. Based on the rules and examples specified above, generate an appropriate response.
            ```
            """;
}
