# Assignment #4

## Group Information

- Degree: M.EIC
- Group id: 06
- Project id: 03
- Students:
    - João Matos, up201703884
    - Tiago Gomes, up201806658

## Use cases
- Create a task without a context or a project
- Complete and delete a task
- Search for task's text

The first two use cases were chosen because they represent the main functionality of the application, which is to create a list of tasks that can be marked as done. The last one was chosen because it's useful for a user to be able to find previously written tasks. By testing these use cases, we make sure that they are working properly.

## Create a task without a context or a project

### State Machine

![](https://i.imgur.com/ei8lnNw.png)

The idle state is the application initial dashboard, where it's possible to see the projects, categories and list of tasks, as well as create new ones. It's possible to create a new task by clicking on the new task text field, where it's possible to choose its title. It's also possible to choose a priority, by clicking on the priority field. When clicking the "+" button or hitting the "Enter" key, the task is created and it's possible to see it on the application dashboard. In this state machine there are 3 states, 10 transitions and 4 events.

### Transition Tree

![](https://i.imgur.com/GHc6NOh.png)

The transition tree describes all the possible paths of this use case. There are 4 different paths.

### Transition Table

| States\\Events     | Click on text field | Click on priority field  | Click on "+" | Hit the "Enter" key | 
| ------------------ | ------------------- | ------------------------ | ------------ | ------------------- | 
| Idle               |     Task Text Edit  | Task Priority Edit       |              |                     | 
| Task Text Edit     | Task Text Edit                    | Task Priority Edit       | Idle         | Idle                | 
| Task Priority Edit |  Task Text Edit                   | Task Priority Edit                         | Idle         | Idle                | 

The transition table of this use case allows us to identify the sneak paths, i.e., identify paths with unspecified behavior. In this case, there are 2 sneak paths.

### Test cases

#### testCreateTaskWithoutPriority

This test aims to check if the create task feature is working properly when there is no associated priority. It starts by checking if the application is on the "Idle" state, by making sure the dashboard is visible and by typing some characters and checking if nothing happens. Then, it goes to the "Task Text Edit", by clicking on the "New Task" text and writing the name of the task. We test if the system is in fact in the "Task Text Edit" state by checking if the new task text is editable. Finally, we create the task, by clicking on the plus button. To test if the task was created, we check if the name we gave to the task is present on the tasks dashboard. Because we didn't gave a priority to the task, we also check if the task was created without a priority. The test passes, which means the feature is working as expected.

![](https://i.imgur.com/A4pKDCt.png)

### testCreateTaskWithPriority

 This test aims to check if the create task with a priority feature is working properly. As the previous test, it starts by checking if the application is on the "Idle" state. Then, it goes to the "Task Text Edit" and checks if it's there. After that, it goes to the "Task Priority Edit", by clicking on the priority field and typing a priority, and checks that state by making sure the priority field is editable. It goes back to the "Task Text Edit" by clicking the task text field, goes again to the "Task Priority Edit" by clicking the priority field and finalizes de creation of the task by clicking the "Enter" key. Finally, it checks if the task was created, by checking the text and the priority of the created task on the tasks dashboard. The test passes, which means the feature is working as expected.

 ![](https://i.imgur.com/oYzWYOv.png)

 ### testOtherTransitions

This test aims to test the transitions that were not used on the previous tests, namely:
- "Hit the Enter key", from "Task Test Edit" to "Idle"
- "Click the +", from "Task Priority Edit" to "Idle"
- "Click on priority field", from "Idle" to "Task Priority Edit"

The test passes, which means the transitions are working as expected.

![](https://i.imgur.com/ZyK72Vd.png)

### testSneakPathIdleEnter

This is a sneak path, because when the application is on the "Idle" state and we click the "Enter" key, the behavior is unexpected. In the test, we start by checking if the application is on the "Idle" state, then we click the "Enter" key, and finally we check if the state did not change. The test passes, which means that, in fact, nothing happens when the application is on the "Idle" state and the user clicks the "Enter" key.

![](https://i.imgur.com/5M5mPos.png)

### testSneakPathIdleClickPlus

This is a sneak path, because when the application is on the "Idle" state and we click the plus button, the behavior is unexpected. As the previous sneak path test, we start by checking if the application is on the "Idle" state, then we click the plus button, and finally we check if the state did not change. The test fails, because the a default task was created. We could say that this is not a sneak path, because the application is expected to create a default task when the user clicks the plus button without editing the task text, but we decided to consider it a sneak path, because a task should have an associated text.

![](https://i.imgur.com/lpYM6BR.png)

## Complete and delete a task

### State Machine

This use case assumes there is a task on the dashboard, which is not marked as done. If the user clicks the check box, the task is marked as done, and if it clicks it again, the task is marked as not done. In the two states, when the user clicks the delete button, the task is deleted from the dashboard. Once a task is deleted, there is no way of going back to the other states. There are 3 states, 4 transitions and 2 events.

![](https://i.imgur.com/Rxe790M.png)

### Transition Tree

The transition tree describes all the possible paths of this use case. There are 3 different paths.

![](https://i.imgur.com/8SLturu.png)

### Transition Table

| States\\Events     | Click the check box on the task | Click the delete button on the task  |
| ------------------ | ------------------------------- | ------------------------------------ |
| Task not done      | Task done                       | Task deleted                         |
| Task done          | Task not done                   | Task deleted                         |
| Task deleted       |                                 |                                      |

This is the transition table for this use case. We can see that there are 2 sneaky paths.

## Search for a task's text

### State Machine

![](./images/search_state_diagram.png)

Like the previous use case, we start with the idle state, which is the state we get to by just opening the app. Here, if we click the search bar that field gains focus and we can start working on our query but no results are shown yet. Here, if we click the "X" icon in the corner of the bar, the bar loses focus and we go back to the initial state. However, if we start typing, only notes that match our query are shown, with the remaining ones being hidden. At this point, we are in a different state (query processing) and if we keep editing the query's text the results will change accordingly. If we hit the "Esc" key, we go back to the query start case where all the notes are shown again but the search field is still focused. Another way of going back to this state is by just deleting all the text in the search bar. Finally, if we click on the "X" icon again, we go back to the idle state. This state machine has 3 states, 7 transitions and 6 events.

### Transition Tree

![](./images/search_transition_tree.png)

This is the transition tree for this use case. There are 4 possible paths. We start in the idle state (Idle_0) and have no choice but to click the search bar and go to the query start state (Query_Start_0). At this point we can go back to the idle state (Idle_1) or go the query processing state (Query_Processing_0). In the query processing state we can go to one of the other 2 states (Query_Start_1 and Idle_2) or stay in the same state (Query_Processing_1).   

### Transition Table

| States\\Events  | Click the search bar | Type something   | Hit the "Esc" key | Edit text        | Delete all the text in the search bar | Click on the "X" icon |
| ---------------- | -------------------- | ---------------- | ----------------- | ---------------- | ------------------------------------- | --------------------- |
| Idle             | Query Start          |                  |                   |                  |                                       |                       |
| Query Start      |                      | Query Processing |                   |                  |                                       | Idle                  |
| Query Processing |                      |                  | Query Start       | Query Processing | Query Start                           | Idle                      |

This is the transition table for this use case. We can see that there are 11 sneaky paths.

### Test suite(s)

Two test suites were created for this use case. One for sneaky paths and another for normal paths. All test cases perform cleanup at the end by closing the application window.

#### Normal paths

This test suite has 7 test cases, one for each transition. All these tests pass.

![](./images/normal_path_suite.png)

##### clickSearch

This case asserts that we are in the Idle state by checking the search bar (in this state the bar just says "Search..."), clicks the search bar and asserts that we are in the Query Start state by checking the search bar again (in this state the bar is completely empty and no prompt is shown) and finishes.

##### typeSomething

This case clicks the search bar, asserts that we are in the Query Start state by checking the search bar, types something (e.g. "asdfasdf") into the search bar and asserts that we are in the Query Processing state by checking that no notes are shown (because no notes have been created, there is no match for any given query).

##### hitEsc

This case clicks the search bar, types something, asserts that we are in the Query Processing state by checking that no notes are shown, hits the "Esc" key and asserts that we are in the Query Start state by checking the search bar.

##### editText

This case click the search bar, types something, asserts that we are in the Query Processing state by checking that no notes are shown, edits the text that is currently in the search bar (e.g. removes some characters and adds more random characters like "afasdf") and asserts that we are still in the Query Processing state by performing the same verification that was previously done.

##### deleteText

This case clicks the search bar, types something, asserts that we are in the Query Processing state, deletes all the text in the search bar and asserts that we are in the Query Start state.

##### clickX1

This case clicks the search bar, asserts that we are in the Query Start state, clicks the "X" icon which is in the corner of the bar and asserts that we are in the Idle state.

##### clickX2

This case clicks the search bar, types something, asserts that we are in the Query Processing state, clicks the "X" icon and asserts that we are in the Idle state.

#### Sneaky paths

This test suite had 3 test cases. They are all performed in the Idle state and the events used are "Type something", "Hit the Esc key" and "Delete all the text in the search bar". All these tests fail.

![](./images/sneaky_path_suite.png)

##### sneakyPathIdleType

This case asserts that we are in the Idle state, types something and asserts that we are still in the Idle state. This last assertion fails because it's actually possible to provide input to the search bar without clicking it first, taking us to the Query Processing state.

##### sneakyPathIdleEsc

This case asserts that we are in the Idle state, hits the "Esc" key and asserts that we are still in the Idle state. This last assertion fails because hitting the "Esc" key actually takes us to the Query Start state even though we had not clicked or typed anything.

##### sneakyPathIdleDelete

This case asserts that we are in the Idle state, tries to delete all the text in the search bar and asserts that we are still in the Idle state. This last assertion fails because we are actually taken to the Query Start state, even though we had not typed anything.

## QF-Test Feedback

Overall, *QF-Test* proved to be very useful when creating test cases that require interaction with the UI. However, one problem that we found is that the configuration file used makes it harder to collaborate with other people when using the tool. The configuration file contains information that is specific to each developer's machine. We edited this file in an attempt to use it in a different machine. Unfortunately, *QF-Test* signs the configuration file and checks its integrity upon loading, making it impossible to use a file that's been edited. Perhaps, if the test suite data was stored in a separate file, it could be used with VCS, while the file with user specific information is ignored.