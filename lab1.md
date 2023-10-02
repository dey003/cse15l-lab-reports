# Lab Report 1

**`cd` with no arguments:**
```
[user@sahara ~]$ cd
[user@sahara ~]$
```
The working directory was `/home` both before and after running the command. This is because with no arguments, the `cd` command does not have a directory to change to, so it stays in the same directory. This output is not an error.

**`cd` with a directory as an argument:**
```
[user@sahara ~]$ cd lecture1
[user@sahara ~/lecture1]$ 
```
The working directory was `/home` before and `/home/lecture1` after running the command. With a directory as the argument, the `cd` command changes to the given directory, which in this case was `lecture1`. This output is not an error.

**`cd` with a file as an argument:**
```
[user@sahara ~/lecture1]$ cd Hello.java
bash: cd: Hello.java: Not a directory
[user@sahara ~/lecture1]$ 
```
The working directory was `/home/lecture1` before and after running the command. Since `cd` is a command for moving between directories, it cannot move to the given file, so it outputs that `Hello.java` is not a directory and does not change directories. This output is an error because `cd` is given an argument that stops the command.

**
