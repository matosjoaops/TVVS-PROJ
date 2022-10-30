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
- Mark a task as done
- Search for task's text

The first two use cases were chosen because they represent the main functionality of the application, which is to create a list of tasks that can be marked as done. The last one was chosen because it's useful for a user to be able to find previously written tasks. By testing these use cases, we make sure that they are working properly.

## Create a task without a context or a project

### State Machine

![](https://i.imgur.com/1ylsSLy.png)

The idle state is the application initial dashboard, where it's possible to see the projects, categories and list of tasks, as well as create new ones. It's possible to create a new task by clicking on the new task text field, where it's possible to choose its title. It's also possible to choose a priority, by clicking on the priority field. When clicking the "+" button or hitting the "Enter" key, the task is created and it's possible to see it on the application dashboard. In this state machine there are 3 states, 8 transitions and 4 events.

### Transition Tree

![](https://i.imgur.com/GHc6NOh.png)

The transition tree describes all the possible paths of this use case. There are 4 different paths.

### Transition Table

| States\\Events     | Click on text field | Click on priority field  | Click on "+" | Hit the "Enter" key | 
| ------------------ | ------------------- | ------------------------ | ------------ | ------------------- | 
| Idle               |     Task Text Edit  | Task Priority Edit       |              |                     | 
| Task Text Edit     |                     | Task Priority Edit       | Idle         | Idle                | 
| Task Priority Edit |                     |                          | Idle         | Idle                | 

The transition table of this use case allows us to identify the sneak paths, i.e., identify paths with unspecified behavior.

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
