package com.clover.recode.domain.recode.dto.code;

public class ExampleCode3 {
    public static String code = """
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = require("fs")
    .readFileSync(filePath)
    .toString()
    .trim()
    .split("\\n");

const N = +input.shift()[0];
const map = input.map((x) => x.split(" "));

const teacher = [];
const temp = [];

for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
        if (map[i][j] === "T") {
            teacher.push([i, j]);
        }

        if (map[i][j] === "X") {
            temp.push([i, j]);
        }
    }
}

const dx = [0, 0, -1, 1];
const dy = [-1, 1, 0, 0];

const search = () => {
    let cnt = 0;
    for (let i = 0; i < teacher.length; i++) {
        const [x, y] = teacher[i];

        for (let d = 0; d < 4; d++) {
            let dist = 1;
            while (true) {
                const nx = x + dist * dx[d];
                const ny = y + dist * dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;

                if (map[nx][ny] === "O") break;

                if (map[nx][ny] === "S") cnt++;

                dist++;
            }
        }
    }

    return cnt;
};

let isPossible = false;
const combi = (depth, idx) => {
    if (isPossible) return;

    if (depth === 3) {
        const res = search();
        if (res === 0) {
            isPossible = true;
        }
        return;
    }

    for (let i = idx; i < temp.length; i++) {
        const [x, y] = temp[i];
        map[x][y] = "O";
        combi(depth + 1, i + 1);
        map[x][y] = "X";
    }
};

combi(0, 0);

console.log(isPossible ? "YES" : "NO");

            """;
}
