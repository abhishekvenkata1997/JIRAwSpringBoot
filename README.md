# JIRAwSpringBoot
A Spring boot enterprise application using Hibernate to build a web page that replicates the use of JIRA a task management system commonly used in MNCs

>>Usage
Built a task management system that can:
1. Login and Register a new User
2. Each user can add a task, assign tasks to other users, set due date to tasks, view and track tasks of other users
3. View activity of a task to check any changes or commits on a task and maintain history of task usage


![image](https://user-images.githubusercontent.com/31111993/208987548-c93f06e9-615c-4717-a0af-03131209cc15.png)
>> System design




<img width="700" alt="image" src="https://user-images.githubusercontent.com/31111993/208987704-ae3ab5f9-0292-4afb-b89d-bcff0127d4e8.png">
 
 >> Classes with Database relationship
 <img width="522" alt="image" src="https://user-images.githubusercontent.com/31111993/208987926-40ae6d83-2cab-4818-9d86-19841e7cd9b2.png">

>> Connection in tables

The User table does not have any of the foreign keys in its own table as it acts as the owning entity for each of these relationships. 
The task table has one column ‘user_id’ which helps in mapping each task to its appropriate user. 
The comment table has two associations with User and Task. It has two foreign keys one identifying the user who added the comment and the other the task to which the comment was added. 
A combination of (1,4) would mean a comment added by the user(One) to task(Four). 
The activity table has a very similar association with the User and Task tables. A combination of (1,4) would mean the activity was done by User(One) to the task(Four). 
