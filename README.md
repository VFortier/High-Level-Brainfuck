# HighLevelBF
Generate Brainfuck code by using a _slightly_ higher level language. Supports 8-bit wrapping brainfuck.

# Planned for Verison 1

## Variables

The only available variable type is `int8` - A 8-bit integer. When assigned a char, it will take the ascii value of it.

### Initialisation

Variable initialisation is to be done in the beginning of the code only.

```
int8 myVar1 = 12
int8 myVar2 = 'A'
```

### Assignment

You can only increment or decrement a variable's value by a fixed amount; it is not possible to assign a fixed value to it. 

```
myVar1 += 10
myVar2 -= 1
```

## If/Else

True if the value of the variable is not zero, otherwise false.

```
if myVar1
	myVar2 += 1
else
	myVar2 += 3
```

## While

While the variable is not zero.

```
while myVar1
	myVar1 -= 1
```

## Read

Not supported.

## Print

Prints the value of the variable as an ASCII character, just like the `.`command in brainfuck.

```
int8 myVar1 = 'B'
print myVar1
```

