# Assignment #9

## Group Information

- Degree: M.EIC
- Group id: 06
- Project id: 03
- Students:
  - João Matos, up201703884
  - Tiago Gomes, up201806658

## WARNING!

Running Pitest takes more than 15 minutes! To mitigate this we added a copy of the final coverage report at the root of the project. Please use it unless you are willing to wait for the aforementioned amount of time.

## Initial Mutation score 

![](./images/Captura%20de%20ecr%C3%A3%20de%202022-12-06%2013-32-51.png)

With all the tests developed in the previous assignments, our line coverage is 85%, the mutation coverage is 84% and the test strength is 97%.

## Equivalent Mutants

Some of the mutators remove calls to print statements inside catch blocks. Print statements don't affect the program's functionality, making these mutants equivalent to the original code.

## Final Mutation Score and Explanations

![](./images/Captura%20de%20ecr%C3%A3%20de%202022-12-07%2016-09-27.png)

This is the final coverage report. The new values for line coverage, mutation coverage and test strength are, respectively, 91%, 91% and 99%. We did not improve coverage further because the leftover mutants are either equivalent (as mentioned in the previous section) or are hard to kill. For example, some mutants have changes in code that runs in separate threads. Testing this kind of code can be difficult and often requires waiting for the threads to run, which would make Pitest take even longer to run.

## Tests Developed

Tests were added to kill mutants in the following classes.

### RelativeDate

Tests were added to verify the calculation of relative dates between two provided dates with varying lenghts of time separating the provided dates. 

### FileModifiedWatcher

Tests were added to check if listeners are alerted and verify the order in which they are alerted.

### Util

A test was added to verify if the contents of an input stream are transfered to a given file.

### TaskIo

Tests were added to check if a buffer's contents are read correctly.

### Filter classes

Tests were added to several Filter classes to make sure the filters are applied correctly when searching for tasks.
