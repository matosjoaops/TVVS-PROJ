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
- Search for task text

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
