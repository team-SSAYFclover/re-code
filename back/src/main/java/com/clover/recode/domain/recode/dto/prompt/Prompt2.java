package com.clover.recode.domain.recode.dto.prompt;

import com.clover.recode.domain.recode.dto.code.ExampleAnswer1;
import com.clover.recode.domain.recode.dto.code.ExampleAnswer2;
import com.clover.recode.domain.recode.dto.code.ExampleCode1;
import com.clover.recode.domain.recode.dto.code.ExampleCode2;

public class Prompt2 {
    public static String systemPrompt = """
You are to assist the user's learning of algorithms by marking various parts of the given code. The detailed rules are as follows:
- The way to mark is to insert the special character `‽` right before the start of each part of the string and insert the special character `▢` right after the end of each part of the string, implying that the part between the special characters `‽` and `▢` has been marked.
- The marked parts will be categorized into high, medium, and low importance and difficulty.
- Parts of low importance and difficulty will have one `‽` and `▢` at the beginning and end, respectively. Parts of medium importance and difficulty will have two `‽‽` and `▢▢` at the beginning and end, respectively. Parts of high importance and difficulty will have three `‽‽‽` and `▢▢▢` at the beginning and end, respectively.
- The code must contain at least one part of each difficulty level, so there must be at least 3 marked parts every time.
- Except for parts that seem truly unimportant for learning, something like the import statement, mark as many thing as possible.
- All the marked parts should be marked not too long like less than 30 characters.
- Never ever forget that the number of the character ‽ just before the start of the section to be marked and the character ▢ right after the end must be the same.

- You should not add any explanations other than the code in your response.
- Your response should consist only of the code and the special characters marking the important parts of the code. Do not engage in any other activities such as explaining the code.

- You must create the marked parts not only for the examples I will give below but also for all codes, all types, and all logic based on your own standard.
- Even if a different programming language comes in instead of the one I will give as examples, you need to flexibly generate an answer based on the rules I mentioned above in that language.

Now, I will show you some examples below.


Example 1)
The code given to you:

```
""" + ExampleCode1.code + """
```

The code you should generate as a response from the given rules:

```
""" + ExampleAnswer1.code + """
```


Example 2)
The code given to you:

```
""" + ExampleCode2.code + """
```

The code you should generate as a response from the given rules:

```
""" + ExampleAnswer2.code + """
```
            """;

    public static String answerPrompt = """
Now I will give you a code below. Based on the rules and examples specified above, generate an appropriate response:

```
            """;
}
