# Lab Report 3
---

## Part 1
I'm testing the `filter` method in `ListExamples`. The associated code for the tests is:
```
import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;

interface StringChecker { boolean checkString(String s); }

class CheckForE implements StringChecker {
    public boolean checkString(String s) {
        if (s.contains("e")) {
            return true;
        } else {
            return false;
        }
    }
}
```
This includes the imports, interface, and class that implements the interface needed to test the `filter` method. The class I wrote returns `true` if the string contains the letter "e", and returns `false` if it doesn't.

A failure inducing input for the method is: 
```
    @Test
    public void testFilter1() {
        List<String> input = new ArrayList<>();
        StringChecker sc = new CheckForE();
        input.add("hi");
        input.add("hello");
        input.add("hey");
        List<String> expected = new ArrayList<>();
        expected.add("hello");
        expected.add("hey");
        assertEquals(expected, ListExamples.filter(input, sc));
    }
```

An input that does not induce an error is:
```
    @Test
    public void testFilter2() {
        List<String> input = new ArrayList<>();
        StringChecker sc = new CheckForE();
        input.add("hi");
        input.add("hello");
        List<String> expected = new ArrayList<>();
        expected.add("hello");
        assertEquals(expected, ListExamples.filter(input, sc));
    }
```

I'm using a bash script called `listtest.sh` to run the tests. The output of the tests being run is:
![Screenshot of test output](lab3images/list_test.png)\
This output shows the symptom that the order in which we expect the strings to be in is the opposite of what they actually are in.

Before the bug is fixed:
```
  static List<String> filter(List<String> list, StringChecker sc) {
    List<String> result = new ArrayList<>();
    for(String s: list) {
      if(sc.checkString(s)) {
        result.add(0, s);
      }
    }
    return result;
  }
```

After the bug is fixed:
```
  static List<String> filter(List<String> list, StringChecker sc) {
    List<String> result = new ArrayList<>();
    for(String s: list) {
      if(sc.checkString(s)) {
        result.add(s);
      }
    }
    return result;
  }
```

The bug is in the line where the string is added to the result. In the original version, the string is added to index 0 of the list, but we want it to add the string to the end of the list, keeping the same order as the original list. We can fix this by removing the first input in the `add` method.

---
## Part 2

I'm researching the command-line options for `grep`. I used the `git help grep` command in VS Code to learn about the options for `grep`.

### -r
This option means to use `grep` recursively on the subdirectories as well as the current directory.
```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -r "science" technical
technical/911report/chapter-13.1.txt:                all-source analysis, and advanced science and technology.
technical/911report/chapter-13.4.txt:                1987, Sufaat received a bachelor's degree in biological sciences, with a minor in
technical/911report/chapter-2.txt:                satisfy its own conscience and justify its existence."
technical/911report/chapter-2.txt:                humanities and social sciences. Many of these young men, even if able to study
technical/biomed/1471-2105-1-1.txt:        biological sciences, and moves toward the 
technical/biomed/1471-2105-1-1.txt:        methods of systems science need to be used to analyze,
technical/biomed/1471-2105-3-22.txt:          manufacturer (Amersham Biosciences, Piscataway, NJ).
technical/biomed/1471-2121-4-3.txt:          Hybond N+ membrane (Amersham Biosciences) by capillary
technical/biomed/1471-213X-1-1.txt:        (Polysciences) resin and sectioned at 10 μm. Sections were
technical/biomed/1471-213X-1-6.txt:        contribution to the body of science, and partly is a common
...
```
Here, `grep` searches within the subdirectories of the `technical` directory, finding files in `911report`, `biomed`, `government` (not shown), and `plos` (not shown).

```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -r "FOREWORD" technical/government
technical/government/About_LSC/Comments_on_semiannual.txt:FOREWORD
technical/government/Gen_Account_Office/Statements_Feb28-1997_volume.txt:FOREWORD
```
In this case, `grep` is searching each subdirectory within the `technical/government` directory, finding files in `About_LSC` and `Gen_Account_Office`.
