# Lab Report 5
---

## Original Post


---
## TA Response

---
## Student Response

---
## Information

### File Structure
The file structure is an updated version of the `list-examples-grader` GitHub repository we used in the Week 6 and Week 9 Lab, which can be seen [here](https://github.com/ucsd-cse15l-s23/list-examples-grader/tree/main). `GradeServer.java` and `Server.java` are unchanged.

`TestListExamples.java` has been edited, adding another `StringChecker` and a test for the `filter` method:
```
import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}

class CheckForE implements StringChecker {
  public boolean checkString(String s) {
    return s.contains("e");
  }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test
  public void testFilter1() {
    List<String> input = Arrays.asList("hi", "hello", "hey");
    StringChecker sc = new CheckForE();
    List<String> expected = Arrays.asList("hello", "hey");
    assertEquals(expected, ListExamples.filter(input, sc));
}
}
```

`grade.sh` has also been completed (with the exception of the bug), grading the given GitHub repository:
```
CPATH=".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar"

# Change CPATH to work for Windows on local and Mac on remote

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission
echo 'Finished cloning'

if [[ -f student-submission/ListExamples.java ]]
then
    echo "Correct file found"
else
    echo "Correct file not found"
    exit
fi

# cp -r student-submission/ListExamples.java TestListExamples.java GradeServer.java Server.java lib grading-area
cp student-submission/ListExamples.java TestListExamples.java GradeServer.java Server.java grading-area

javac -cp $CPATH grading-area/*.java
if [[ $? -ne 0 ]]
then
    echo "Files did not compile"
    exit
else
    echo "Files successfully compiled"
fi

cd grading-area
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-results.txt
tests_total=`grep -oP "Tests run: \K\d+" test-results.txt`
tests_failed=`grep -oP "Failures: \K\d+" test-results.txt`
tests_passed="$((tests_total-tests_failed))"
if [[ ${#tests_total} -ne 0 ]]
then
    echo "$tests_passed tests passed out of $tests_total"
    grade=$((tests_passed/tests_total))
    echo "Grade: $grade"
else
    echo "All tests passed"
    echo "Grade: 100"
fi
```
