# Implementing Access-Controlled Use Cases in a Digital University System

## Team Members

| Name | NU ID | Role |
|------|-------|------|
| Vaishnavi Srinivas | 002519030 | Student |
| Srija Pallavarpu | 002590733 | Admin |
| Pradyumna Reddy Cherla | 003151209 | Faculty |
| Agastya Venkata Sai Gandra | 002517855 | Registrar |

---

## 3. Project Overview

### Purpose
The system allows universities to manage students, faculty, registrar operations, and administrators in a unified interface with role-based authentication.

### Objectives
- Streamline academic and administrative operations
- Provide clear separation of privileges by role
- Automate course offerings, enrollments, and tuition tracking

### Key Features
- Role-based Login (Admin, Faculty, Student, Registrar)
- Course Offering Creation and Assignment
- Student Enrollment and Tuition Tracking
- Faculty Management and Grading
- Reporting and Analytics (Enrollment, Revenue, GPA)
- Data Integrity through Directory Classes

---

## 4. Installation & Setup Instructions

### Prerequisites
- Java JDK 17 or higher
- NetBeans IDE 16
- GitHub Desktop / Gitbash

### Setup Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/vaishrao2601/Group_Lab_20.git
   ```
2. Open the project in NetBeans IDE
3. Ensure the `src/info5100/university/example` directory structure is intact
4. Clean and Build the project
5. Run the project

---

## 5. Authentication & Access Control

- **Authentication** is handled by `AuthManager` which validates the credentials username and password
- **Access Control** is handled by `UserAccount`. Once login succeeds, the system uses the `UserAccount` from the session to determine the role

### Authorization Rules

| Role | Access Rights |
|------|---------------|
| **Admin** | Manage all directories (Users, Faculty, Students, Courses). Full CRUD over Persons, User logins, student and faculty directory. Access to department and courses. |
| **Faculty** | View assigned courses, grade students, update profiles. |
| **Student** | Register for courses, view grades, manage profile, pay tuition. |
| **Registrar** | Manage course offerings, assign faculty, handle student enrollments, generate financial and institutional reports |

---

## 6. Features Implemented

### Admin
- Register new Persons (Students, admin, faculty, registrar)
- Create User accounts for the above Persons
- Manage own profile by updating details
- Manage students/faculty/registrar: View, update, delete, search. Assign faculty to a course
- Generate overall academic reports: Role based active users, Courses offered per semester, Enrolled students by course per semester, Total tuition by semester

### Faculty
- Manage assigned courses, update details of the courses, upload/modify syllabus, open/close enrollment for the course
- Manage enrolled students, grade assignments and generate grade, rank students and show class GPA
- Tuition collected from enrolled students for the course
- Performance report
- Manage own profile details (editing)

### Student
- Browse available courses and enroll
- Drop courses and check tuition balance
- View grades and academic progress

### Registrar
- Create and edit course offerings per semester, assign faculty, set room/time slots, and define enrollment
- Enroll/drop students in courses
- Track tuition payments for students, and generate department-wise revenue per semester
- Reports for department enrollment and GPA distribution
- View and update own profile details (name, email, office hours, contact)

---

## 7. Usage Instructions

### For Admin
1. **Register Persons** → Add new persons (students, faculty, registrar, admin). Details like name, Department, Role, Contact no., Email, ID
2. **Students** → View, update, delete or search for registered students. Details like Student ID, name, department, email, contact info, academic status
3. **Manage Faculty** → View, update, delete and search for registered faculty. Also assign faculty to courses. Details like ID, name, department, email, contact info, assigned course
4. **Manage Registrar** → View, update, delete registrar records. Details like ID, name, department, Email, Contact Info
5. **Administer User Accounts** → Create and maintain user accounts. Details like Name, Username, Password, Role
6. **Reports** → Generate summary statistics (user count, enrollments, tuition)

### For Faculty
1. **Manage Courses** → Displays all assigned courses with course ID, name, semester, capacity, and enrollments
2. **Manage Student Profiles** → Select a course → View students' academic progress and attendance
3. **Performance Reports** → Generate GPA and grade distribution reports per course or term
4. **Tuition Enrollment** → Monitor which students have completed payment before final grade submission
5. **Manage Profile** → Update faculty contact information or office hours

### For Students
1. **Registration** → Displays available course offerings → Select course → Enroll or Drop
2. **Course Work** → Shows current semester subjects with grades and instructors
3. **Graduation Audit** → Compare earned credits vs. required credits to graduate
4. **Pay Tuition** → Review tuition amounts and balances → Mark payment status
5. **Transcript** → Displays completed courses, GPA, and earned credits in tabular format
6. **Manage Profile** → Edit personal details

### For Registrar
1. **Manage Course Offering** → Select semester → Add/Edit offerings → Assign faculty and set capacity and room/time
2. **Student Registration** → Enroll students into offerings or drop as needed (affects seat availability and tuition)
3. **Tuition Finances Reconciliation** → View total tuition collected and pending balances per student and department
4. **Reports** → Generate enrollment statistics, tuition summaries, and academic performance metrics
5. **Manage Profile** → Update registrar details
6. **Back** → Return to Registrar Work Area

---

## 8. Testing Guide

### Test 1: Login Verification

| Input | Expected Output |
|-------|----------------|
| Correct credentials | Redirects to role-specific work area |
| Incorrect credentials | Displays "Invalid credentials" dialog |

### Test 2: Registration

| Action | Expected Result |
|--------|----------------|
| Admin registers a person (student/faculty/registrar) | The person actually gets stored in the respective directory (person/student/faculty directories) based on their role and success message is displayed |

### Test 3: Enrollment

| Action | Expected Result |
|--------|----------------|
| Student enrolls in a full course | Shows "No seats available." |
| Student enrolls in an open course | Updates course seat count and tuition balance |

### Test 4: Tuition Reconciliation

| Action | Expected Result |
|--------|----------------|
| Registrar generates revenue report | Displays per-course and total tuition summary |

---

## 9. Challenges & Solutions

| Challenge | Solution |
|-----------|----------|
| Handling null panel references in CardLayout | Passed `CardSequencePanel` correctly from parent constructor |
| Data synchronization across roles | Used shared directory instances (PersonDirectory, StudentDirectory, etc.) |
| GPA and academic standing not updating correctly across semesters | Implemented centralized GPA calculation in StudentProfile and refreshed TranscriptJPanel dynamically |
| Tuition tracking per student | Introduced `StudentAccount` class linked to `StudentProfile` |

---

## 10. Future Enhancements

- Add database persistence (MySQL) instead of in-memory storage
- Implement an email notification system for enrollment updates
- Extend to Web-based UI (Spring Boot / React)
- Add GPA analytics dashboard
- Support multiple semesters and cross-department courses

---

## 11. Contribution Breakdown

| Member Name | Contribution |
|-------------|--------------|
| **Vaishnavi Srinivas** | Worked on the Student Role module. Designed and implemented all UI panels including Student Work Area, Course Registration, Coursework Management, Transcript Review, Graduation Audit, Tuition Payment, and Profile Management. Configured layouts, variable names, and button flows to maintain consistency across screens. Developed functionality for course search, enrollment, and drop with an 8-credit-hour limit per semester, tuition calculation, and payment processing with refund handling. Implemented GPA computation and academic standing logic, graduation progress audit, and transcript filtering by semester. Integrated financial and academic modules to enable real-time data synchronization. Ensured students can track academic progress, manage tuition, and maintain personal details through a complete end-to-end workflow. |
| **Srija Pallavarapu** | Implemented the complete Admin module responsible for managing users and core system configuration. Modified Person, PersonDirectory, UserAccount, UserAccountDirectory and related directories for registration, role assignment, and credential validation. Built a university configuration file. The main features include registering new persons, managing students, faculty, and registrar records, and administering user accounts with proper role assignments and reporting data. The Admin module acts as the central hub for access control and person management. Edited README file. |
| **Pradyumna Reddy Cherla** | Created and wired the UI for the Faculty role and all associated JPanels, including course management, student profiles, performance reports, tuition/enrollment, and profile management. Contributed to the model classes in CourseCatalog, CourseSchedule, and Persona.Faculty to support faculty‐specific functionality such as course assignment, grading, and enrollment handling. Created and edited the ReadMe file. |
| **Agastya Venkata Sai Gandra** | Created and wired the Registrar Role UI with all functional JPanels, including course offering management, student registration, tuition and finance reconciliation, and reporting panels. Contributed to the Finance, Department, and College packages, as well as the CourseSchedule module, to support semester scheduling, faculty assignment, and tuition tracking. Integrated registrar workflows for course management, enrollment, and financial reporting. Edited README file. |

---

## Repository

[GitHub Repository Link](https://github.com/vaishrao2601/Group_Lab_20.git)
