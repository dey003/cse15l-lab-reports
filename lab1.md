# Lab Report 1
---
**`cd` with no arguments:**
```
[user@sahara ~]$ cd
[user@sahara ~]$
```
The working directory was `/home` both before and after running the command. It appears as though the `cd` command, which changes directories, did not do anything because it was not given a directory to change to. However, this is not the case, as we can see in another example:
```
[user@sahara ~/lecture1]$ cd
[user@sahara ~]$
```
Here, the working directory was `/home/lecture1` before the command and `/home` after the command. This reveals the function of `cd` with no argument, which is to change to the home directory. This output is not an error.

---
**`cd` with a directory as an argument:**
```
[user@sahara ~]$ cd lecture1
[user@sahara ~/lecture1]$ 
```
The working directory was `/home` before and `/home/lecture1` after running the command. With a directory as the argument, the `cd` command changes to the given directory, which in this case was `lecture1`. This output is not an error.

---
**`cd` with a file as an argument:**
```
[user@sahara ~/lecture1]$ cd Hello.java
bash: cd: Hello.java: Not a directory
[user@sahara ~/lecture1]$ 
```
The working directory was `/home/lecture1` before and after running the command. Since `cd` is a command for moving between directories, it does not accept a file as an argument, so it outputs that `Hello.java` is not a directory and does not change directories. This output is an error because `cd` is given an argument that stops the command.

---
**`ls` with no arguments:**
```
[user@sahara ~]$ ls
lecture1
[user@sahara ~]$ 
```
The working directory was `/home`. The `ls` command lists the items in a directory, so when it is not given an argument, it lists the items in the current directory, which was `lecture1` in this case. This output is not an error.

---
**`ls` with a directory as an argument:**
```
[user@sahara ~]$ ls lecture1
Hello.class  Hello.java  messages  README
[user@sahara ~]$ 
```
The working directory was `/home`. When `ls` is given a directory as an argument, it lists the items in that directory, which were `Hello.class`, `Hello.java`, `messages`, and `README`. This output is not an error.

---
**`ls` with a file as an argument:**
```
[user@sahara ~/lecture1]$ ls README
README
[user@sahara ~/lecture1]$ 
```
The working directory was `/home/lecture1`. When `ls` is given a file as an argument, it lists the file name, which was `README` in this case. This output is not an error.

---
**`cat` with no arguments:**
```
[user@sahara ~]$ cat
input
input
clear
clear
[user@sahara ~]$ 
```
The working directory was `/home`. The `cat` command normally prints the text inside a file, but when no arguments are given, it repeats whatever you type into the terminal until you end the command by using the keyboard shortcut `ctrl+d`. In this example, I typed "input" and "clear", which the `cat` command repeated, before ending the command using `ctrl+d`. This output is not an error.

---
**`cat` with a directory as an argument:**
```
[user@sahara ~]$ cat lecture1
cat: lecture1: Is a directory
[user@sahara ~]$ 
```
The working directory was `/home`. Since `cat` prints text inside a file, it doesn't accept a directory as an argument, and outputs that `lecture1` is a directory. This output is an error because `cat` is given an argument that stops the command.

---
**`cat` with a file as an argument:**
```
[user@sahara ~/lecture1/messages]$ cat zh-cn.txt
你好世界

[user@sahara ~/lecture1/messages]$ 
```
The working directory was `/home/lecture1/messages`. `cat` correctly printed out the text in the file `zh-cn.txt` that was given as the argument. This output is not an error.
