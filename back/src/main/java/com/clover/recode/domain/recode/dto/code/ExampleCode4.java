package com.clover.recode.domain.recode.dto.code;

public class ExampleCode4 {
    public static String code = """
            import java.util.*;
             import java.io.*;
            
             public class Main {
            
                 static class Node{
                     int x, y;
                     public Node(int x, int y){
                      this.x = x;
                      this.y = y;
                     }   }
            
                 static int n, cnt, arrCnt=0, redGreenCnt=0;
                 static char currentColor;
                 static int[] dx= {1,-1,0,0}; //상하좌우
                 static int[] dy= {0,0,-1,1};
            
                 static char[][] arr, arr2;
                 static boolean[][] visited, visited2 ;
            
                 public static void main(String[] args) throws IOException {
            
                     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
                     n = Integer.parseInt(br.readLine());
            
                     arr = new char[n][n];
                     arr2 = new char[n][n];
                     visited = new boolean[n][n];
                     visited2 = new boolean[n][n];
            
                     String str;
                     for(int i=0; i<n; i++) {
                         str = br.readLine();
                         for (int j = 0; j < n; j++) {
                             arr[i][j] = str.charAt(j);
            
                             if(str.charAt(j)=='G'){
                                 arr2[i][j] = 'R';
                             } else{
                                 arr2[i][j] = str.charAt(j);
                             }
            
                         }
                     }
            
            
                     for(int i=0; i<n; i++){
                         for(int j=0; j<n; j++) {
                             if (!visited[i][j]) {
                                 bfs(i, j);
                                 arrCnt++;
                             }
                         }
                     }
            
                     for(int i=0; i<n; i++){
                         for(int j=0; j<n; j++) {
                             if (!visited2[i][j]) {
                                 bfs2(i, j);
                                 redGreenCnt++;
                             }
                         }
                     }
            
                     System.out.print(arrCnt+" "+redGreenCnt);
                 }
            
                 static void bfs(int i, int j) {
            
                     visited[i][j] = true;
                     currentColor = arr[i][j];
                     Queue<Node> q = new ArrayDeque();
                     q.add(new Node(i,j));
                     cnt=1;
            
                     while(!q.isEmpty()){
            
                         Node node = q.poll();
            
                         for(int k=0; k<4; k++) {
                             int nx = node.x + dx[k];
                             int ny = node.y + dy[k];
            
                             if(nx>=0 && ny>=0 && nx<n && ny<n){
            
                                 if(arr[nx][ny] == currentColor && !visited[nx][ny]) {
                                     cnt+=1;
                                     visited[nx][ny] = true;
                                     q.add(new Node(nx,ny));
                                 }
                             }
                         }
            
                     }
                 }
            
                 static void bfs2(int i, int j) {
            
                     visited2[i][j] = true;
                     currentColor = arr2[i][j];
                     Queue<Node> q = new ArrayDeque();
                     q.add(new Node(i,j));
            
            
                     while(!q.isEmpty()){
            
                         Node node = q.poll();
            
                         for(int k=0; k<4; k++) {
                             int nx = node.x + dx[k];
                             int ny = node.y + dy[k];
            
                             if(nx>=0 && ny>=0 && nx<n && ny<n){
            
                                 if(arr2[nx][ny] == currentColor && !visited2[nx][ny]) {
                                     visited2[nx][ny] = true;
                                     q.add(new Node(nx,ny));
                                 }
                             }
                         }
            
                     }
                 }
            
             }
            """;
}
