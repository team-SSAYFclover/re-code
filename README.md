<p align="center">
<img src="./docs/Readme%20assets/logo2.png" />
</p>

# 나만의 작은 알고리즘 복습 서비스, re:code

-   삼성 청년 SW 아카데미(SSAFY) 10기 자율 프로젝트
-   구미 2반 10팀 : 팀 SSAYFclover
-   2024.04.08. ~ 2024.05.20.
-   [프로젝트 노션](https://lshhh.notion.site/4-SSAYF-102c6e5bf59e4b2db9e516985ccbd983)

<div align="end">

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fteam-SSAYFclover%2Fre-code&count_bg=%2353EDC7&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)


</div>

## 목차

1. [**웹 서비스 소개**](#1)
1. [**기술 스택**](#2)
1. [**시스템 구조**](#3)
1. [**주요 기능**](#4)
1. [**서비스 화면**](#5)
1. [**팀원 소개**](#6)
1. [**디렉터리 구조**](#7)
1. [**산출물**](#8)

<br/>

<div id="1"></div>

## ✨ 웹 서비스 소개

**re:code**는 사용자가 등록한 알고리즘 문제 코드를 기반으로, 등록한 코드에 대해 AI를 사용하여 해당 코드의 주요 로직을 분석하고 주기적으로 해당 코드에 빈칸을 제공함으로써, 사용자에게 해당 알고리즘을 상기시키고 스스로 점검을 가능하게하여 사용자의 알고리즘 학습을 돕는 서비스입니다.

### [re:code 바로가기](https://www.recode-d210.com)

<br/>

<div id="3"></div>

## 🛠️ 기술 스택

<div>
<h4>🎨 FrontEnd </h4>
<img src="./docs/Readme%20assets/frontend.png" />
<h4>💾  BackEnd</h4>
<img src="./docs/Readme%20assets/backend.png" />
<h4>💻 Infra</h4>
<img src="./docs/Readme%20assets/infra.png" />
<h4>💬 Cooperation</h4>
<img src="./docs/Readme%20assets/tools.png" />
</div>

<br/>

<div id="4"></div>

## ⚙️ 시스템 구조

<img src="./docs/Readme%20assets/architecture.png" />

<br/>

<img src="./docs/Readme assets/recodeERD.png"/>

<br/>

<div id="5"></div>

## 💡 주요 기능

<table style="word-break:keep-all">
<tr ><th style="text-align:center;">기능</th><th>내용</th></tr>
<tr>
  <td style="text-align:center;">문제 등록</td>
  <td>re:code 크롬 익스텐션을 다운로드하고 서비스와 연동하여 온라인 저지 사이트 백준에서 자신이 맞춘 문제를 re:code 서비스에 등록할 수 있습니다.</td>
</tr>
<tr>
  <td style="text-align:center;">리마인드 알림</td>
  <td>re:code 서비스에서 해당 날 복습할 문제가 있다면 사용자에게 알림을 전송합니다.</td>
</tr>
<tr>
  <td style="text-align:center;">사용자 설정</td>
  <td>사용자는 알림 전송 시간, 복습 난이도 설정을 변경할 수 있습니다.</td>
</tr>
<tr>
  <td style="text-align:center;">복습 리스트 등록</td>
  <td>사용자가 re:code 서비스에 등록한 코드를 대상으로 1일 뒤, 3일 뒤, 일주일 뒤 간격으로 복습리스트에 해당 코드의 복습 문제를 등록합니다.</td>
</tr>
<tr>
  <td style="text-align:center;">문제 풀기</td>
  <td>복습 리스트에 등록된 문제를 풀 수 있습니다. 각 복습 문제를 완료할 때마다 사용자의 네잎클로버가 성장하고 통계량이 증가합니다.</td>
</tr>
<tr>
  <td style="text-align:center;">문제 조회</td>
  <td>사용자가 re:code 서비스에 등록한 문제와 코드를 조회할 수 있습니다.</td>
</tr>
<tr>
  <td style="text-align:center;">복습 등록</td>
  <td>사용자가 등록한 코드를 복습 리스트에 등록하여 복습할 수 있습니다.</td>
</tr>

</table>

<br/>

<div id="6"></div>

## 🖥️ 서비스 화면

### re:code 메인 화면

-   re:code에서 제공하는 다양한 통계를 확인할 수 있습니다.

![Untitled](./docs/Readme%20assets/gif/dashBoard.gif)

<br/>

### 시작 가이드

-   직관적이고 사용자 친화적인 서비스 이용 가이드 제공

![Untitled](./docs/Readme%20assets/gif/guide.gif)

<br/>

### 사용자 설정 변경

-   알림 수신 설정, 시간 설정, 복습 난이도 설정을 변경할 수 있습니다.

![Untitled](./docs/Readme%20assets/gif/changeSetting.gif)

<br/>

### 크롬 확장 프로그램을 통한 문제 생성

-   크롬 웹 스토어에 등록된 확장 프로그램 다운 가능
-   백준 사이트에서 자신이 맞춘 문제의 코드를 가져와 문제 생성

![Untitled](./docs/Readme%20assets/gif/regist_recode.gif)

<br/>

### 리마인드 알림

-   복습리스트에 풀 문제가 있다면 사용자가 설정한 시간대에 알림을 받을 수 있습니다.

![Untitled](./docs/Readme%20assets/gif/alarm.gif)

<br/>

### 문제 조회

-   등록한 코드와 문제를 조회
-   알고리즘, 난이도 등 다양한 검색 기능

![Untitled](./docs/Readme%20assets/gif/detail.gif)

<br/>

### 문제 풀기

-   등록한 코드에 ai를 통한 빈칸을 생성

![Untitled](./docs/Readme%20assets/gif/recode.gif)

<br/>

<div id="6"></div>

## 🧑‍🤝‍🧑 팀원 소개

| <img src="https://github.com/gyeongri.png" width="150"> | <img src="https://github.com/tmdwns7809.png" width="150"> | <img src="https://github.com/bae2019.png" width="150"> | <img src="https://github.com/ChaNyeok1225.png" width="150"> | <img src="https://github.com/rosielsh.png" width="150"> | <img src="https://github.com/eunalove.png" width="150"> |
| :-----------------------------------------------------: | :-------------------------------------------------------: | :----------------------------------------------------: | :---------------------------------------------------------: | :-----------------------------------------------------: | :-----------------------------------------------------: |
|   [정경리<br>@gyeongri](https://github.com/gyeongri)    |  [이승준<br>@tmdwns7809](https://github.com/tmdwns7809)   |    [배성연<br>@bae2019](https://github.com/bae2019)    | [전찬혁<br>@ChaNyeok1225](https://github.com/ChaNyeok1225)  |   [이수화<br>@rosielsh](https://github.com/rosielsh)    |   [오은아<br>@eunalove](https://github.com/eunalove)    |
|                       Leader, BE                        |                         BE, Infra                         |                           FE                           |                      BE, FE, Extention                      |                           FE                            |                        BE, Infra                        |

<br/>

<div id="7"></div>

## 📁 디렉토리 구조

### FrontEnd

```
📁 SRC
├─apis
├─assets
│  ├─clover
│  │  └─star
│  │      ├─css
│  │      └─scss
│  ├─guide
│  ├─lotties
│  └─tier
├─components
│  ├─@common
│  ├─alarm
│  ├─home
│  │  └─cloverbgComp
│  ├─login
│  ├─my
│  ├─problem
│  └─recode
│      ├─Modal
│      └─Tooltip
├─hooks
│  ├─@common
│  ├─alarm
│  ├─home
│  ├─problem
│  ├─recode
│  └─user
├─mocks
│  └─api
│      ├─data
│      └─handlers
├─pages
│  ├─error
│  ├─guide
│  ├─home
│  ├─private
│  ├─problem
│  ├─recode
│  └─redirect
├─services
│  ├─alarm
│  ├─home
│  ├─problem
│  ├─recode
│  └─user
├─stores
├─types
│  └─model
└─utils
```

### BackEnd

```
📁 MAIN
├─java
│  └─com
│      └─clover
│          └─recode
│              ├─domain
│              │  ├─auth
│              │  │  ├─dto
│              │  │  └─service
│              │  ├─fcmtoken
│              │  │  ├─entity
│              │  │  ├─repository
│              │  │  └─service
│              │  ├─problem
│              │  │  ├─controller
│              │  │  ├─dto
│              │  │  ├─entity
│              │  │  ├─repository
│              │  │  └─service
│              │  ├─recode
│              │  │  ├─controller
│              │  │  ├─dto
│              │  │  │  ├─code
│              │  │  │  ├─gpt
│              │  │  │  └─prompt
│              │  │  ├─entity
│              │  │  ├─repository
│              │  │  └─service
│              │  ├─statistics
│              │  │  ├─controller
│              │  │  ├─dto
│              │  │  │  └─response
│              │  │  ├─entity
│              │  │  ├─repository
│              │  │  ├─scheduler
│              │  │  └─service
│              │  └─user
│              │      ├─controller
│              │      ├─dto
│              │      ├─entity
│              │      ├─repository
│              │      └─service
│              └─global
│                  ├─config
│                  ├─jwt
│                  ├─oauth
│                  ├─redis
│                  └─result
│                      └─error
│                          └─exception
└─resources
```

<br/>

<div id="8"></div>

## 산출물

### [1. 기능 명세서](https://lshhh.notion.site/66ccf6261812440fbf7f15ff6c6ad82f?pvs=4)

### [2. 화면 설계서](https://www.figma.com/design/EZJpS35ArEUQuQdOkZQiMu/Re%3Acode?node-id=0-1)

### [3. API 명세서](https://lshhh.notion.site/API-API-c5b6c294b6e74ec5abb4fa7a266b5072?pvs=4)

### [4. ERD](https://lshhh.notion.site/ERD-fc2004b19aa74e7588afe50be1ceccb4?pvs=4)
