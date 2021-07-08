# Todo App

---

This is a simple, light-weight web based app which lets one keep track of tasks that they need to accomplish during the day
The Objective of this project is to perform functional testing of this web app.

---

### Application Link: _https://todomvc.com/examples/angular2/_

---

### Test Case Matrix

---

| TC ID   |      Description      |  Positive/ Negative |
|----------|-------------|------|
| 1 | URL is working | Positive |
| 2 | Verify the Title of the WebApp | Positive |
| 3 | User is able to **'Add New'** todo item in the WebApp  | Positive |
| 4 | User is able to **'Edit'** an existing todo item in the WebApp  | Positive |
| 5 | User is able to **'Add Multiple Todos'** in the WebApp| Positive |
| 6 | Number of todos added by the matches the number of items left on the WebApp | Positive |
| 7 | Todo task added first in the app appears at the top in the list | Positive |
| 8 | User can add todo tasks in language other than English | Positive |
| 9 | User is able to mark as completed multiple todo tasks | Positive |
| 10 | Number of todos still exist when the page is reloaded or refreshed | Negative |
| 11 | User can add Duplicate tasks in the WebApp | Negative |
| 12 | User cannot add blank entries | Negative |
| 13 | User can add special characters in the todos | Negative |
| 14 | Todo items are removed when a new browser session is launched on the same machine | Negative |

---

### Project Structure

---

- **Test Case Path**--> src/test/java
- **Test Classes**--> PositiveTests & NegativeTests
- **WebDriver Classes**--> src/main/java/drivers
- **Driver location**--> src/main/java/resources

---

### Tech Stack

---
- TestNG
- Selenium WebDriver
- Maven
- HttpURLConnection
- Maven surefire reports

---



