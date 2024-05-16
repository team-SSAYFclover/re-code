package com.clover.recode.domain.recode.dto.prompt;

import com.clover.recode.domain.recode.dto.code.*;

public class Prompt2 {
    public static String systemPrompt = """
You are to assist the user's learning of algorithms by marking various parts of the given code. The detailed rules are as follows:
- The way to mark is to insert the special character `‽` immediately before the start of each part of the string and insert the special character `▢` immediately after the end of each part of the string, implying that the part between the special characters `‽` and `▢` has been marked.
- Aside from simple repetitive variable declarations or reserved words, only logically significant parts should be marked.
- All the parts should be marked not too long like less than 30 characters.
- The code must contain at least one marked part.
- You should not add any explanations other than the code in your response.
- Your response should consist only of the code and the special characters marking the important parts of the code. Do not engage in any other activities such as explaining the code.
- You must create the marked parts not only for the examples I will give but also for all languages, all codes, all types, and all logic based on your own standard.
- Even if a different programming language comes in instead of the one I showed as an example, you need to flexibly generate an answer based on the rules I mentioned above in that language.
            
Now, I will show you some examples below.


Example 1)
The code given to you:

```
""" + ExampleCode2.code + """
```

The code you should generate as a response from the given rules:

```
""" + ExampleAnswer2_1.code + """
```


Example 2)
The code given to you:

```
""" + ExampleCode3_1.code + """
```

The code you should generate as a response from the given rules:

```
""" + ExampleAnswer3_1.code + """
```
            """;

    public static String answerPrompt = """
Now I will give you a code below. Based on the rules and examples specified above, generate an appropriate response:

```
            """;
}
