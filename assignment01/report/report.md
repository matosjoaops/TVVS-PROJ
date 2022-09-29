# Assignment #1

## Group Information

- Degree: M.EIC
- Group id: 06
- Project id: 03
- Students:
  - João Matos, up201703884
  - Tiago Gomes, up201806658

## Project description

### What is it?

The jdotxt project is an implementation of a todo application. It contains a GUI where it is possible to insert new tasks and marked them as completed. The information is saved in a text file in a location chosen by the user.

### How is the source-code organized?

The project contains two packages at the root of the source code:

#### com.chschmid.jdotxt

This package contains the entrypoint of the application along with 2 other packages. One with utilities and the other with the code responsible for building the GUI. The latter has its own subpackages with more utilities and code for the controls of the UI.

#### com.todotxt.todotxttouch

This package contains some constants and exception related code at its root. Furthermore, it contains 2 subpackages, one for utilities and another for task related code. This code is responsible for parsing, filtering and sorting (in its own package) the tasks created by the user.


## What static testing is and why it is import and relevant?

Static testing is a technique which allows to check for faults in a software application and associated documents without requiring it to be executed, enabling faster feedback. This technique is usually used to find faults in the code which could make it less maintainable.

## Static testing tools used

### CheckStyle

This tool is used to make sure the Java code in a project follows the styling rules that have been defined.


#### Configuration

To use CheckStyle in the project, we executed the following steps:
- Add the CheckStyle configuration to the pom.xml file, as explained in the [Recitation #1](https://paginas.fe.up.pt/~jcmc/tvvs/2022-2023/recitations/recitation-1.html);
- Create the file `checkstyle-rules.xml` on the `rulesets` folder;
- Change the following configurations on the `checkstyle-rules.xml` file:
  - ![](https://i.imgur.com/AM4VHRr.png)
    - The indentation values were changed to better match those used by the project;
  - ![](https://i.imgur.com/QIROY95.png)
    - By default, CheckStyle does not allow the use of tabs, this was changed since the project does make use of tabs.


#### Report summary

The report emitted by Checkstyle is composed by the summary, files, rules and details sections.

In the summary section, it is possible to see the number of files, number of warnings and number of errors. In the case of the jdotxt project, there are 68 files, 12481 warnings and 0 errors.

The files section exposes a list with more information about each file, namely the number of warnings and number of errors.

The section about the rules has information about the rules used by Checkstyle, showing the number of violations and the severity of each one. For the jdotxt project, the rules with the highest number of violations are the *Indentation* and the *FileTabCharacter*.

Lastly, the details section shows every warning and error, with a message and its location.


#### Bugs solved

##### Warning *EmptyCatchBlock*

CheckStyle was throwing a warning because of the empty catch blocks present on the source code.

![](https://i.imgur.com/v47j7s3.png)

![](https://i.imgur.com/PTUAblp.png)

To fix this warning, we decided to add a line which prints the stack trace. This way, when an exception is catched, the programmer can better understand the source of the problem.

![](https://i.imgur.com/tGPmx5l.png)

##### Warning *AbbreviationAsWordInName*

CheckStyle alerted us to the existence of a function with a name that didn't follow the naming convention properly and had 2 consecutive capital letters.

![](https://i.imgur.com/FrtfVOh.png)

![](https://i.imgur.com/ZDXBTPN.png)

![](https://i.imgur.com/CO1ditp.png)

##### Warning *NeedBraces*

CheckStyle presented a warning about the use of braces ({}) on the if constructs.

![](https://i.imgur.com/MYlvj0y.png)

![](https://i.imgur.com/yElAhHi.png)

To fix this warning, we added braces on the corresponding if constructs.

![](https://i.imgur.com/nYECKe9.png)

##### Warning *NoWhitespaceBefore*

CheckStyle was throwing a warning because of the whitespace before the ";".

![](https://i.imgur.com/e4IDa3s.png)

![](https://i.imgur.com/NqiLcjE.png)

To fix this issue, we removed the whitespace.

![](https://i.imgur.com/itNjsO6.png)

##### Warning *Indentation*

CheckStyle alerted us about the value of the indentation: it should be 8 instead of 4. We could change the ruleset to fix this problem, but as the majority of the source code was using an indentation level of 8, we decided to maintain it.

![](https://i.imgur.com/dA22kOB.png)

![](https://i.imgur.com/WysAuUt.png)

To fix this issue, we changed the indentation level from 4 to 8.

![](https://i.imgur.com/oszrh6e.png)

#### Report after fixes

After configuring the rules and fixing the 5 bugs, Checkstyle was throwing 1947 warnings and 0 errors.


### PMD

PMD is a tool used to report issues on the source code of an application. It has predefined rules and gives the possibility to costumize the ruleset.

#### Configuration

To use PMD in the project, we executed the following steps
- Add the PMD configuration to the pom.xml file, as explained in the [Recitation #1](https://paginas.fe.up.pt/~jcmc/tvvs/2022-2023/recitations/recitation-1.html);
- Copy the PMD ruleset files used in the class to the `rulesets` folder;
- Change the plugin configuration in order to enable and disable some rulesets:
  - ![](https://i.imgur.com/t94vbN9.png)
    - Controversial rules were disabled since some of them did not make sense and were even marked as deprecated by PMD's own documentation.

#### Report summary

Before applying our custom configuration, the original report contained multiple violations with priority 1. The ones with the most violations were *AvoidUsingShortType* and *VariableNamingConventions*.
For priority 2, we mostly see violations related to *SystemPrintln* and *AvoidReassigningParameters* but we didn't see a large amount of violations with this priority level.
For priority levels 3-5, there is a large variety of violations being reported across the entire project.
The report also contains the file and line where each violation is detected, along with a short description.


#### Bugs solved

##### Warning *ClassWithOnlyPrivateConstructorsShouldBeFinal*

PMD was throwing a warning because of a class which only has private constructors. In this case, the class should be marked as final.

![](https://i.imgur.com/4rSzYvf.png)

![](https://i.imgur.com/DUR92Ah.png)

To fix this warning, we added the final keyword to the class.

![](https://i.imgur.com/KH9MlUC.png)

##### Warning *MethodNamingConventions*

PMD was throwing a warning because a method was not following camel case convention.

![](https://i.imgur.com/LoRjGAW.png)

![](https://i.imgur.com/TyYB16z.png)

To fix this warning, we added the changed the first character of the method name from upper to lower case.

![](https://i.imgur.com/l4joN1U.png)

##### Warning *AvoidReassigningParameters*

PMD was throwing a warning saying there was a parameter reassigment.

![](https://i.imgur.com/IHpWCHr.png)

![](https://i.imgur.com/iABuVJj.png)

To fix this warning, we created a new variable to be assigned instead of the parameter.

![](https://i.imgur.com/ERgJr93.png)

##### Warning *LocalVariableCouldBeFinal*

PMD showed us a warning regarding a local variable that could be declared as final.


![](https://i.imgur.com/je1g27x.png)

![](https://i.imgur.com/4kMvt4s.png)

To fix the warning, the "final" keyword was added in the declaration.

![](https://i.imgur.com/miENx1b.png)


##### *ImmutableField*

PMD showed us a warning regarding a field whose value was never reassigned and could, therefore, be made final.

![](https://i.imgur.com/REmok68.png)

![](https://i.imgur.com/8mw8MMf.png)

The "final" keyword was added, which made the warning go away.

![](https://i.imgur.com/b0liiNi.png)

#### Report after fixes

After applying our configuration, all violations related to the controversial ruleset disappeared (the most notable absence was *AvoidUsingShortType*). For the fixes, the individual instances that we addressed also disappeared.

