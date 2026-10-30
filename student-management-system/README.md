# 🎓 Student Management System

> A console-based Java application to manage student records using a MySQL database.
> Built as **Task 1 (Easy)** of the **Auspify Technologies Software Development Internship**.

---

## 📌 Project Name

**Student Management System (Java + MySQL)**

---

## 📝 Project Description

The Student Management System is a simple and efficient application that helps store and manage student records digitally instead of using paper registers. It allows the user to add new students, view all records, update details, delete records, and search students by name.

All data is stored permanently in a **MySQL database**, so records remain safe even after the program is closed. The application uses **JDBC** to connect Java with MySQL and **PreparedStatement** to keep database queries secure.

This project demonstrates core concepts of **CRUD operations**, **database connectivity**, **input validation**, and **error handling**.

---

## 🛠️ Technologies Used

| Component | Technology |
|---|---|
| Programming Language | Java (JDK 8 or above) |
| Database | MySQL |
| Database Connectivity | JDBC (MySQL Connector/J 8.0.33) |
| Database Tool | MySQL Workbench |
| Editor / IDE | Notepad / VS Code |
| Version Control | Git & GitHub |

---

## ✨ Features

- ➕ **Add Student:** Store a new student record (Roll No, Name, Course, Phone)
- 📋 **View Students:** Display all records in a neat table format
- ✏️ **Update Student:** Modify details of an existing student
- 🗑️ **Delete Student:** Remove a student record
- 🔍 **Search Student:** Find students by name (full or partial)
- ✅ **Input Validation:** Handles empty names, non-numeric roll numbers, and duplicate roll numbers
- 💾 **Persistent Storage:** Data is saved in MySQL, not lost after closing the program
- 🔒 **Secure Queries:** Uses `PreparedStatement` to prevent SQL injection

---

## 🗄️ Database Structure

**Database:** `student_db`  **Table:** `students`

| Column | Data Type | Description |
|---|---|---|
| roll_no | INT | Primary Key |
| name | VARCHAR(100) | Student name (required) |
| course | VARCHAR(50) | Course name |
| phone | VARCHAR(15) | Phone number |

> The table is created automatically when the program runs for the first time.

---

## 📁 Project Structure

```
student-management-system/
 ├── StudentApp.java
 ├── README.md
 └── screenshots/
      ├── home.png
      ├── add.png
      ├── update.png
      ├── search.png
      └── delete.png
```

---

## 📸 Screenshots

### Home Menu
![Home Menu](screenshots/home.png)

### Add Student
![Add Student](screenshots/add.png)

### Update Student and View Records
![Update Student](screenshots/update.png)

### Search Student
![Search Student](screenshots/search.png)

### Delete Student
![Delete Student](screenshots/delete.png)

---

## ⚙️ Installation Steps

### 1. Install the prerequisites

- **JDK** (Java Development Kit). Check with:
  ```
  java -version
  ```
- **MySQL Server** and **MySQL Workbench**
- **MySQL Connector/J** jar file (`mysql-connector-j-8.0.33.jar`)

### 2. Clone the repository

```
git clone https://github.com/ansh-zero/<your-repository-name>.git
cd <your-repository-name>
```

*(Or simply download the project as a ZIP and extract it.)*

### 3. Add the MySQL Connector

Download `mysql-connector-j-8.0.33.jar` and place it in the same folder as `StudentApp.java`:

```
https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar
```

### 4. Create the database

Open MySQL Workbench and run:

```sql
CREATE DATABASE student_db;
```

### 5. Configure your MySQL credentials

Open `StudentApp.java` and update these lines with your own details:

```java
static final String URL = "jdbc:mysql://localhost:3306/student_db";
static final String USER = "root";
static final String PASSWORD = "your_password";
```

---

## ▶️ How to Run the Project

Open Command Prompt / Terminal inside the project folder.

**Step 1: Compile**
```
javac StudentApp.java
```

**Step 2: Run**

Windows:
```
java -cp ".;mysql-connector-j-8.0.33.jar" StudentApp
```

Mac / Linux:
```
java -cp ".:mysql-connector-j-8.0.33.jar" StudentApp
```

On successful connection you will see:

```
MySQL se connect ho gaya!

===== Student Management System =====
1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Search Student
6. Exit
```

> **Note:** MySQL Server must be running. If you get `Communications link failure`, start the MySQL service (`net start MySQL80` on Windows).

---

## 🧪 Testing Performed

- Added multiple student records
- Viewed, updated, and deleted records
- Searched students by partial name
- Tried duplicate Roll No, empty name, and non-numeric Roll No (errors handled correctly)
- Verified data in MySQL Workbench using `SELECT * FROM students;`

---

## 🚀 Future Improvements

- Java Swing GUI for a better user interface
- Admin login system
- Export student records to CSV / PDF
- Additional fields (email, address, marks, attendance)

---

## 📚 What I Learned

- CRUD operations using JDBC
- Connecting Java with a MySQL database
- Writing secure queries with `PreparedStatement`
- Input validation and exception handling
- Testing and debugging a database application

---

## 📬 Contact

**GitHub:** [https://github.com/ansh-zero](https://github.com/ansh-zero)

Feel free to open an issue or reach out for suggestions and feedback.

---

## 🏢 About the Internship

This project was completed as part of the **Software Development Internship** at **Auspify Technologies**.

`#Auspify` `#AuspifyTechnologies` `#AuspifyInternship` `#AuspifyProjects`
