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
This option means to use `grep` recursively on the current directory and its subdirectories.
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
Here, `grep` searches within the subdirectories of the `technical` directory, finding files in `911report`, `biomed`, `government` (not shown), and `plos` (not shown). This allows us to search the entire directory at the same time, rather than going through each subdirectory.
```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -r "FOREWORD" technical/government
technical/government/About_LSC/Comments_on_semiannual.txt:FOREWORD
technical/government/Gen_Account_Office/Statements_Feb28-1997_volume.txt:FOREWORD
```
In this case, `grep` is searching each subdirectory within the `technical/government` directory, finding files in `About_LSC` and `Gen_Account_Office`. This is another example of saving effort going through each subdirectory.

### -i
This option tells `grep` to ignore the capitalization of the searched pattern.
```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -i "foreword" technical/government/About_LSC/Comments_on_semiannual.txt 
FOREWORD
```
Although the searched string is "foreword" in lowercase, `grep` finds the uppercase "FOREWORD" in the file. When we don't know how the word might be capitalized, we can search for every instance with any capitalization.
```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -i "congress" technical/government/Gen_Account_Office/Testimony_cg00010t.txt
Mr. Chairman, Congressman Turner, and Members of the
highlight the important role that GAO plays to support the Congress
international concerns that confront our nation and the Congress,
the ways in which GAO can support the Congress now and in the
straightforward: GAO exists to support the Congress in meeting its
operations of the War Department and other agencies. The Congress
making the agency's work more useful to the Congress.
The growth in congressionally based work, combined with the
...
```
By searching for the string "congress", we can find terms where "Congress" is capitalized as well as terms including "congress" that aren't capitalized, such as "congressionally". When we don't care about how the word is capitalized, this option is very useful.

### -v
This option outputs the lines without the searched pattern, rather than the ones that match.
```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -v "guidelines" technical/plos/pmed.0020191.txt





        The excellent article by Jordan Paradise, Lori B. Andrews, and colleagues, “Ethics.
        Constructing Ethical Guidelines for Biohistory” [1], neither advocates nor argues against
        biohistorical research; instead, it points out that such investigations are currently
        governments, medical examiners, family members, or intrepid biographers are to be given
        permission? Who is to decide what is “historically significant”? Not to mention the
        meta-question: who is to decide who is to decide? I apologize to the authors if my brief
        comments [2] implied that they took a position on this issue.




```
Here, `grep` outputs every line except for ones containing the string "guidelines" (including empty lines). If we wanted to filter out a specific phrase, this option is useful.
```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -v "'" technical/plos/pmed.0020226.txt





        trials” and concentrate on “critically evaluating them.” This bold and radical suggestion
        strive to better enforce their conflicts-of-interest disclosure rules, drug companies will
        strive to find or create other publication outlets that can communicate to physicians
        precisely what advertisers wish to communicate. In sum, an unanticipated effect of purging
        clinical trial reports from medical journals might be an even larger proliferation of frank




```
The output for `grep` here is every line without an apostrophe (again including empty lines). This is a similar example to the one above, showing that we can filter out anything we want.

### -l
This option tells `grep` just to output the file names with matches, rather than each line.
```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -l "LSC" technical/government/*/*.txt
technical/government/About_LSC/Comments_on_semiannual.txt
technical/government/About_LSC/commission_report.txt
technical/government/About_LSC/conference_highlights.txt
technical/government/About_LSC/CONFIG_STANDARDS.txt
technical/government/About_LSC/diversity_priorities.txt
technical/government/About_LSC/LegalServCorp_v_VelazquezDissent.txt
technical/government/About_LSC/LegalServCorp_v_VelazquezOpinion.txt
technical/government/About_LSC/LegalServCorp_v_VelazquezSyllabus.txt
technical/government/About_LSC/ODonnell_et_al_v_LSCdecision.txt
technical/government/About_LSC/ONTARIO_LEGAL_AID_SERIES.txt
technical/government/About_LSC/Progress_report.txt
technical/government/About_LSC/Protocol_Regarding_Access.txt
technical/government/About_LSC/reporting_system.txt
technical/government/About_LSC/Special_report_to_congress.txt
technical/government/About_LSC/State_Planning_Report.txt
technical/government/About_LSC/State_Planning_Special_Report.txt
technical/government/About_LSC/Strategic_report.txt
technical/government/Media/Avoids_Budget_Cut.txt
technical/government/Media/Barr_sharpening_ax.txt
technical/government/Media/BergenCountyRecord.txt
technical/government/Media/BusinessWire.txt
technical/government/Media/Butler_Co_attorneys.txt
...
```
The command outputs every file that contains the string "LSC". If we want to know what files a phrase is mentioned in, this is a useful option.
```
devan@DevanHP MINGW64 ~/OneDrive/Documents/GitHub/docsearch (main)
$ grep -l "Congress" technical/government/*/*.txt
technical/government/About_LSC/Comments_on_semiannual.txt
technical/government/About_LSC/commission_report.txt
technical/government/About_LSC/diversity_priorities.txt
technical/government/About_LSC/LegalServCorp_v_VelazquezDissent.txt
technical/government/About_LSC/LegalServCorp_v_VelazquezOpinion.txt
technical/government/About_LSC/LegalServCorp_v_VelazquezSyllabus.txt
technical/government/About_LSC/ODonnell_et_al_v_LSCdecision.txt
technical/government/About_LSC/ONTARIO_LEGAL_AID_SERIES.txt
technical/government/About_LSC/Progress_report.txt
...
```
Every file with "Congress" in it is outputted. Since the term "Congress" appears many, many times in a lot of these files, this option is useful when we just want to know whether the file contains the term.
