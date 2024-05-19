package com.clover.recode.domain.recode.dto.code;

public class ExampleAnswer3_1 {
    public static String code = """
import java.io.*;
 import java.util.*;

 public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int sdir = Integer.parseInt(st.nextToken());

        int[][][] board = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }

        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[] { sx, sy });

        int clean = 0;
        boolean flag = false;

        while (‽!q.isEmpty()▢) {
            int[] p = ‽q.pop()▢;

            flag = false;
            if (‽board[p[0]][p[1]][1] == 0▢) {
                ‽board[p[0]][p[1]][1] = 1▢;
                ‽clean++▢;
            }

            for (int dir = 1; dir < 5; dir++) {
                int nx = ‽p[0]▢ + ‽dx[(8 + sdir - dir) % 4]▢;
                int ny = ‽p[1]▢ + ‽dy[(8 + sdir - dir) % 4]▢;

                if (‽nx < 0▢ || ‽ny < 0▢ || ‽n <= nx▢ || ‽m <= ny▢)
                    ‽continue▢;
                if (‽board[nx][ny][0] == 1▢ || ‽board[nx][ny][1] == 1▢)
                    ‽continue▢;

                flag = true;
                q.add(new int[] { nx, ny });
                ‽sdir▢ = ‽(8 + sdir - dir) % 4▢;
                ‽break▢;
            }

            if (‽!flag▢) {
                int nx = ‽p[0]▢ + ‽dx[(sdir + 2) % 4]▢;
                int ny = ‽p[1]▢ + ‽dy[(sdir + 2) % 4]▢;

                if (‽nx < 0▢ || ‽ny < 0▢ || ‽n <= nx▢ || ‽m <= ny▢ || ‽board[nx][ny][0] == 1▢)
                    ‽break▢;

                q.add(new int[] { nx, ny });
            }

        }

        System.out.println(clean);

    }

 }
            """;
}
