package com.clover.recode.domain.recode.dto;

public class EnglishPrompt {
    public static String systemPrompt = """
You are to assist the user's learning of algorithms by marking various parts of the given code, excluding parts that you think are truly not meaningful for learning. The detailed rules are as follows:
- Except for parts that seem truly unimportant for learning, like the import statement, mark everything else.
- All the parts should be divided not too long.
- The way to mark is to insert the special character `‽` immediately before the start of each part of the string and insert the special character `▢` immediately after the end of each part of the string, implying that the part between the special characters `‽` and `▢` has been marked.
- The marked parts will be categorized into high, medium, and low importance and difficulty.
- Parts of low importance and difficulty will have one `‽` and `▢` at the beginning and end, respectively. Parts of medium importance and difficulty will have two `‽‽` and `▢▢` at the beginning and end, respectively. Parts of high importance and difficulty will have three `‽‽‽` and `▢▢▢` at the beginning and end, respectively.
- The code must contain at least one part of each difficulty level.
- That is, any code must have at least one part surrounded by the low-difficulty special characters `‽` and `▢`, one part surrounded by the medium-difficulty special characters `‽‽` and `▢▢`, and one part surrounded by the high-difficulty special characters `‽‽‽` and `▢▢▢` for a total of at least three parts.
- **Never ever forget that the number of the character ‽ just before the start of the section to be marked and the character ▢ right after the end must be the same.**
- You should not add any explanations other than the code in your response.
- Your response should consist only of the code and the special characters marking the important parts of the code. Do not engage in any other activities such as explaining the code.
- You must create the marked parts not only for the examples I will give but also for all codes, all types, and all logic based on your own standard.
- **Never ever forget to create as many marked parts as possible**
            
Now, I will show you some examples below.


Example 1)
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

The code you should generate as a response from the given rules:

```
import java.io.BufferedReader;
import java.io.InputStreamReader;
            
public class Main {
    public static void main(String[] args) throws Exception {
        ‽BufferedReader▢ br = ‽new BufferedReader▢(‽‽new InputStreamReader▢▢(‽‽System.in▢▢));
        
        ‽int[]▢ arr = ‽new int▢[‽‽9▢▢];
        
        ‽int▢ sum = ‽0▢;
        ‽for▢ (‽int▢ i = ‽‽0▢▢; i ‽‽< 9▢▢; i‽++▢) {
            ‽‽arr[i]▢▢ = ‽‽Integer.parseInt▢▢(‽‽br.readLine()▢▢);
            ‽‽sum▢▢ ‽+=▢ ‽‽arr[i]▢▢;
        }
        
        ‽mainloop▢: ‽for▢ (‽int▢ i = ‽‽0▢▢; i ‽‽‽< 8▢▢▢; i‽++▢) {
            ‽for▢ (‽int▢ j = ‽‽‽i+1▢▢▢; j ‽‽‽< 9▢▢▢; j‽++▢) {
                ‽‽if▢▢ ((‽‽‽sum-arr[i]-arr[j]▢▢▢) ‽‽==▢▢ ‽‽‽100▢▢▢) {
                    ‽for▢ (‽int▢ j2 = ‽‽0▢▢; j2 ‽‽‽< 9▢▢▢; j2‽++▢) {
                        ‽‽if▢▢ (‽‽‽j2!=i▢▢▢‽‽&&▢▢‽‽‽j2!=j▢▢▢) {
                            ‽System▢.‽out▢.‽println▢(‽‽‽arr[j2]▢▢▢);
                        }
                    }
                    ‽‽‽break▢▢▢ ‽‽mainloop▢▢;
                }
            }
        }
    }
}
```


Example 2)
The code given to you:

```
#include <algorithm>
#include <cmath>
#include <iostream>
#include <vector>
using namespace std;

struct Pos
{
    int x, y;
};
int N, M;
int MIN = 987654321; // 출력할 도시의 치킨 거리 최솟값
bool selected[13];
vector<Pos> house_pos;
vector<Pos> chicken_pos;
vector<Pos> picked;

int Distance(Pos a, Pos b)
{
    return abs(a.x - b.x) + abs(a.y - b.y);
}

void Find_Min_Dist()
{
    int result = 0;
    for (int i = 0; i < house_pos.size(); i++) // 각 집으로부터
    {
        int min_dist = 987654321;
        for (int j = 0; j < picked.size(); j++) // 모든 고른 치킨집에 대해
            min_dist = min(min_dist, Distance(house_pos[i], picked[j])); // 최소 치킨거리 찾아
        result += min_dist; // 최소 도시의 치킨거리 구함
    }
    MIN = min(MIN, result);
}

void Find_M_Combination(int x, int m)
{
    if (m == M)
        Find_Min_Dist();

    // 치킨집 M개 고르자
    for (int i = x; i < chicken_pos.size(); i++)
    {
        selected[i] = true;
        picked.push_back({ chicken_pos[i].x, chicken_pos[i].y });
        Find_M_Combination(i + 1, m + 1);
        selected[i] = false;
        picked.pop_back();
    }
}

int main()
{
    // 입력
    cin >> N >> M;
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
        {
            int tmp;
            cin >> tmp;
            if (tmp == 1)
                house_pos.push_back({ i, j });
            else if (tmp == 2)
                chicken_pos.push_back({ i, j });
        }
    Find_M_Combination(0, 0);
    cout << MIN << endl;
    return 0;
}
```

The code you should generate as a response from the given rules:

```
#include <algorithm>
#include <cmath>
#include <iostream>
#include <vector>
using namespace std;

‽struct▢ ‽Pos▢
{
    ‽int▢ x, y;
};
‽int▢ N, M;
‽int▢ MIN = ‽‽987654321▢▢; // 출력할 도시의 치킨 거리 최솟값
‽bool▢ selected[‽‽13▢▢];
‽‽vector<Pos>▢▢ house_pos;
‽‽vector<Pos>▢▢ chicken_pos;
‽‽vector<Pos>▢▢ picked;

‽‽int▢▢ Distance(‽Pos▢ a, ‽Pos▢ b)
{
    ‽return▢ ‽‽abs▢▢(‽‽‽a.x▢▢▢ ‽‽-▢▢ ‽‽‽b.x▢▢▢) ‽‽+▢▢ ‽‽abs▢▢(‽‽‽a.y▢▢▢ ‽‽-▢▢ ‽‽‽b.y▢▢▢);
}

‽‽void▢▢ Find_Min_Dist()
{
    int result = ‽‽0▢▢;
    ‽‽for▢▢ (‽int▢ i = ‽‽0▢▢; i ‽‽<▢▢ ‽‽house_pos.size()▢▢; i‽++▢) // 각 집으로부터
    {
        ‽int▢ min_dist = ‽‽987654321▢▢;
        ‽‽for▢▢ (‽int▢ j = ‽‽‽0▢▢▢; j ‽‽<▢▢ ‽‽picked.size()▢▢; j‽‽++▢▢) // 모든 고른 치킨집에 대해
            ‽min_dist▢ = ‽‽min▢▢(‽‽min_dist▢▢, ‽‽Distance▢▢(‽‽‽house_pos[i]▢▢▢, ‽‽‽picked[j]▢▢▢)); // 최소 치킨거리 찾아
        ‽result▢ ‽‽+=▢▢ ‽‽min_dist▢▢; // 최소 도시의 치킨거리 구함
    }
    ‽‽MIN▢▢ = ‽‽min▢▢(‽MIN▢, ‽result▢);
}

‽‽void▢▢ Find_M_Combination(‽int▢ x, ‽int▢ m)
{
    ‽‽if▢▢ (‽‽m▢▢ ‽‽==▢▢ ‽‽M▢▢)
        ‽‽‽Find_Min_Dist()▢▢▢;

    // 치킨집 M개 고르자
    ‽‽for▢▢ (‽int▢ i = ‽‽‽x▢▢▢; i ‽‽<▢▢ ‽‽chicken_pos.size()▢▢; i‽++▢)
    {
        ‽‽‽selected[i]▢▢▢ = ‽‽true▢▢;
        ‽‽picked▢▢.‽push_back▢({ ‽‽chicken_pos[i].x▢▢, ‽‽chicken_pos[i].y▢▢ });
        ‽‽‽Find_M_Combination▢▢▢(‽‽‽i + 1▢▢▢, ‽‽‽m + 1▢▢▢);
        ‽‽‽selected[i]▢▢▢ = ‽‽false▢▢;
        ‽‽picked▢▢.‽pop_back()▢;
    }
}

int main()
{
    // 입력
    ‽cin▢ ‽>>▢ ‽N▢ ‽>>▢ ‽M▢;
    ‽for▢ (‽int▢ i = ‽0▢; i ‽<▢ ‽N▢; i‽++▢)
        ‽‽for▢▢ (‽int▢ j = ‽0▢; j ‽<▢ ‽N▢; j‽++▢)
        {
            ‽int▢ tmp;
            ‽cin▢ ‽>>▢ ‽tmp▢;
            ‽‽if▢▢ (‽‽‽tmp == 1▢▢▢)
                ‽‽‽house_pos▢▢▢.‽‽push_back▢▢(‽‽‽{ i, j }▢▢▢);
            ‽else if▢ (‽‽tmp == 2▢▢)
                ‽‽chicken_pos▢▢.‽‽push_back▢▢(‽‽{ i, j }▢▢);
        }
    ‽‽‽Find_M_Combination▢▢▢(‽‽0, 0▢▢);
    ‽cout▢ ‽<<▢ ‽MIN▢ ‽<<▢ ‽endl▢;
    return 0;
}
```
            """;

    public static String answerPrompt = """
Now I will give you a code below. Based on the rules and examples specified above, generate an appropriate response:

```
            """;
}
