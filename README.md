
# Pyramid (pyrmd)

🚧 This project is currently under construction 🚧

Pyramid is a simple interpreter based esolang built on Java.
Pyramid focuses on functions. In a sense,
every pyrmd declaration is a function... variables, logic, etc. Despite its cool name, Pyramid is
somewhat painful to program in, afterall this is an esolang 👍.

## Sample pyrmd
Pyramid utlizes similar operators to many other languages. Pyramid utlizes curly braces to
define multiline code in its functions.

| Name               | Operator | Use Case                                       |
|--------------------| ------------- |------------------------------------------------|
| Define             | -> | defines a function                             |
| Is equal to        | =  | logical comparison operator                    |
| Is not equal to    | !=  | inverse logical comparison operator            |
| End statment       | ;  | ends a piece of code                           |
| Comment start      | @  | starts a mutliline comment                     |
| Comment end        | @  | signifies the end of a multiline comment       |
| Addition Op        | +  | used to add 2 values                   |
| Subtraction Op     | -  | used to subtract 2 values                      |
| Multiplication Op  | *  | used to multiply 2 values               |
| Division Op        | /  | used to divide 2 values                |
| Value to string Op | '  | shifts a value to ascii (req start)            |
| String Op          | "  | shifts letters to ascii values (req start & back) |
| value Separator    | ,  | separates vlaues, used in arguments and arrays |

| Type  | Keyword
| ------------- | ------------- | 
| Integer  | int |
| Integer Array  | int[] | 
| No return  | void  | 

### Sample 1, Hello 34: ###
```
int a -> 34;

@ square brackets define type array @
int[] b -> "Hello"; @ strings are treated as arrays in pyrmd @

@ the print function will auto convert to ascii,
to save a number we must provide the ascii value or use the value to string op @
print(b + " " + 'a);

```

### Sample 2, function time ###
```
int myVar -> 97;

void myFunction -> int value1, int value2 {
    print('value1 + " " + 'value2);
}

myFunction(45, myVar); @ parens are used to feed arguments @
```

### Wait, function logic? ###
```
void myFirstLogic -> 1 != 2 {
    print("statement true");
}

myFirstLogic; @ you dont need parens all the time (: @
```

### Variables behind the scenes ###
```
@ what's the user sees (is a shortcut) @
int a -> 3;

@ what really is happening @
int a -> {return 3;}
```

## Contributing ##
- Java 11 is required to work on this project
- My response may be slow, pull requests may take a while to be merged
- Commit naming convention:
  - Types of commit: `[feat], [fix], [doc]`
  - Commit body: `[type] + description`
  - Commit example: `[feat] Added new feature`
  - Further convention info: https://www.conventionalcommits.org/en/v1.0.0/
