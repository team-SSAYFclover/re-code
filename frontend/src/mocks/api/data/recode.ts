import { APIResponse } from '@/types/model';
import { IGetRecodeRes, IGetTodayRecodeListRes } from '@/types/recode';

export const getTodayRecodeList: APIResponse<IGetTodayRecodeListRes> = {
  status: 200,
  message: '오늘의 복습리스트 조회에 성공했습니다.',
  data: {
    todayProblems: [
      {
        problemId: 3302,
        codeId: 10,
        name: 'Job Scheduling',
        reviewCnt: 2,
        completed: true,
      },
      {
        problemId: 11779,
        codeId: 12,
        name: '최소비용 구하기 2',
        reviewCnt: 2,
        completed: true,
      },
      {
        problemId: 1486,
        codeId: 3,
        name: '등산',
        reviewCnt: 1,
        completed: false,
      },
      {
        problemId: 1261,
        codeId: 5,
        name: '알고스팟',
        reviewCnt: 2,
        completed: true,
      },
      {
        problemId: 11559,
        codeId: 13,
        name: 'Puyo Puyo',
        reviewCnt: 1,
        completed: false,
      },
      {
        problemId: 1197,
        codeId: 13,
        name: '최소 스패닝 트리',
        reviewCnt: 3,
        completed: false,
      },
      {
        problemId: 17471,
        codeId: 29,
        name: '게리맨더링',
        reviewCnt: 1,
        completed: true,
      },
    ],
  },
};

export const getRecode: APIResponse<IGetRecodeRes> = {
  status: 200,
  message: '레코드 조회에 성공했습니다.',
  data: {
    problem: {
      problemId: 16234,
      codeId: 12,
      name: '인구 이동',
      level: 12,
      completed: false,
      reviewCnt: 3,
      tags: ['구현', '그래프 이론', '그래프 탐색', '시뮬레이션', '너비 우선 탐색'],
      content:
        '## 문제\n<p>N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다. 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다. 인접한 나라 사이에는 국경선이 존재한다. 모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.</p>\n\n<p>오늘부터 인구 이동이 시작되는 날이다.</p>\n\n<p>인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.</p>\n\n<ul>\n\t<li>국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.</li>\n\t<li>위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.</li>\n\t<li>국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.</li>\n\t<li>연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.</li>\n\t<li>연합을 해체하고, 모든 국경선을 닫는다.</li>\n</ul>\n\n<p>각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.</p>\n\n## 입력\n<p>첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)</p>\n\n<p>둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)</p>\n\n<p>인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.</p>\n\n## 출력\n<p>인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.</p>\n\n',
    },
    recode:
      'import java.io.BufferedReader;\r\nimport java.io.InputStreamReader;\r\nimport java.util.ArrayDeque;\r\nimport java.util.Arrays;\r\nimport java.util.Stack;\r\nimport java.util.StringTokenizer;\r\n\r\npublic class Main {\r\n\r\n  public static void main(String[] args) throws Exception {\r\n\r\n    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\r\n    StringTokenizer st;\r\n\r\n    st = new StringTokenizer(br.readLine());\r\n    int N = Integer.parseInt(st.nextToken());\r\n    int L = Integer.parseInt(st.nextToken());\r\n    int R = Integer.parseInt(st.nextToken());\r\n\r\n    int[] p = new int[N*N]; // 서로소집합\r\n    int[][] map = new int[N][N]; // 인구 맵\r\n\r\n    int idx = 0;\r\n    for(int i = 0; i < N; i++) {\r\n      st = new StringTokenizer(br.readLine());\r\n      for (int j = 0; j < N; j++) {\r\n        map[i][j] = Integer.parseInt(st.nextToken());\r\n        p[idx] = idx++;\r\n      }\r\n    }\r\n\r\n    int[] mP = new int[N*N];\r\n    boolean isContinue = true;\r\n    int[] dx = {1,0}, dy = {0,1};\r\n    int[][] sumMap = new int[N][N];\r\n    int[][] cnt = new int[N][N];\r\n    int ans = 0;\r\n    int pIdx;\r\n    while(‽▢) {\r\n      isContinue = ‽▢;\r\n      for(int i = 0; i < N; i++) {\r\n        Arrays.fill(‽▢, 0);\r\n        Arrays.fill(cnt[i], 0);\r\n      }\r\n      mP = p.clone();\r\n\r\n      for(int i = 0; i < N; i++) {\r\n        for(int j = 0; j < N; j++) {\r\n          int tx = i + 1;\r\n          int ty = j + 1;\r\n\r\n          if(tx < N) {\r\n            int diff = Math.abs(map[i][j] - map[tx][j]);\r\n            if (‽▢ && union(i * N + j, tx * N + j, mP)) {\r\n              if(!isContinue) {\r\n                ans++;\r\n                isContinue = true;\r\n              }\r\n            }\r\n          }\r\n\r\n          if(ty < N) {\r\n            int diff = Math.abs(map[i][j] - map[i][ty]);\r\n            if (‽▢ && union(i * N + j, i * N + ty, mP)) {\r\n              if(!isContinue) {\r\n                ans++;\r\n                isContinue = true;\r\n              }\r\n            }\r\n          }\r\n        }\r\n      }\r\n\r\n      pIdx = 0;\r\n      for(int i = 0; i < N; i++) {\r\n        for(int j = 0; j < N; j++) {\r\n          int pnum = find(pIdx, mP);\r\n          sumMap[pnum / N][pnum % N] += map[i][j];\r\n          cnt[pnum / N][pnum % N]++;\r\n          pIdx++;\r\n        }\r\n      }\r\n\r\n      pIdx = 0;\r\n      for(int i = 0; i < N; i++) {\r\n        for(int j = 0; j < N; j++) {\r\n          int pnum = find(pIdx, mP);\r\n          int c = cnt[pnum / N][pnum % N];\r\n          if(c > 1) {\r\n            map[i][j] = sumMap[pnum / N][pnum % N] / ‽▢;\r\n          }\r\n          pIdx++;\r\n        }\r\n      }\r\n\r\n    }\r\n\r\n    System.out.println(ans);\r\n\r\n  }\r\n\r\n  static int find(int a, int[] p) {\r\n    if(a == p[a]) return a;\r\n    return p[a] = find(p[a], p);\r\n  }\r\n\r\n  static boolean union(int a, int b, int[] p) {\r\n    a = find(a, p);\r\n    b = find(b, p);\r\n\r\n    if(a == b) return false;\r\n\r\n    p[b] = a;\r\n    return true;\r\n  }\r\n\r\n',
    answers: [
      'isContinue',
      'false',
      'sumMap[i]',
      'L <= diff && diff <= R',
      'L <= diff && diff <= R',
      'c',
    ],
  },
};
