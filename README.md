# HighLevelBF
Generate Brainfuck code by using a _slightly_ higher level language I made up, **BfGen**. Supports 8-bit wrapping brainfuck.

# Examples
A program that prints the alphabet, one character per line, but replaces every three character by "!" (i.e. C, F, I, ...).

## The BfGen Code (the one you write)

```
int8 letter = 'A'
int8 countDown = 26
int8 lineFeedAscii = 10
int8 isPrintLetter = 2
int8 exclamation = '!'

while countDown
	if isPrintLetter
		print letter
		isPrintLetter -= 1
	else
		print exclamation
		isPrintLetter += 2

	print lineFeedAscii
	letter += 1
	countDown -= 1
```

## The Generated Brainfuck Code

[Try it online!](https://tio.run/##SypKzMxLK03O/v9fGR1wAbFCmGOQp6OTj2uwAlQETQ2XNqXAjos4ORDbjgsEiTAS06UwD3n6BYcEhTqHePr74fAT2Fs2YMAVzaWgYGcHJEAMBQWgkB6YYWdnpwtl6NoAGbEgddpwdXZ2ejD12toQEV07qDKoGUAZkB5drliu//8B)

```
#################
### VARIABLES ###
#################

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++>
++++++++++++++++++++++++++>
++++++++++>
++>
>
>
+++++++++++++++++++++++++++++++++>

####################
### INSTRUCTIONS ###
####################

<<<<<<
[
  >>
  [
    <<<.
    >>>-
    >>-<
  ]
  >+
  [
    >>.
    <<<++
    >->
  ]
  <<<.
  <<+
  >-
]
```

# Verison 1 - Features

## Variables

The only available variable type is `int8` - A 8-bit integer. When assigned a char, it will take the ascii value of the char.

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

Not supported in V1.

## Print

Prints the value of the variable as an ASCII character, just like the `.`command in brainfuck.

```
int8 myVar1 = 'B'
print myVar1
```

