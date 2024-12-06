Project Title: justInvest User Authentication and Access Control System

Caleb 101187217
Description:
The System consists of 5 main classes and main text files.  The system allows you to create an account with your
name password and role.  Then you can login using your credentials and depending on the role, you can do the actions
depending on what permissions your role has.  There are 6 different user roles, Clients, Premium Clients, Employees,
Financial Planners, Financial Advisors, and Tellers.  A password system was also implemented allowing for encryption of
the password and the searching and inputting the password into a text file.

How to Run:
cd into the JustInvest project folder
java -jar JustInvest-1.0-SNAPSHOT.jar

note:
i believe when you run the jar, the password file might have all the test in which I put the
entries into the file, so if you try to create a user with those information, it wont work, but i will
delete them when i push my git code so it shouldnt be in the passwwd.txt file.

Where the files are:
under src/main/java/org.example : are all my runnign files
under test/java/org.example : are my 4 tests
My jar file is right in the JustInvest project folder

Problem 1:
code is the different class files that have the access control mechanism

Problem 2:
PasswordManager is the source code file and PasswordManagerTest are the tests

Problem 3:
EnrollUser is the source code file and EnrollUserTest are the tests

Problem 4:
LoginUser and AccessControlMech are the source code files and ACMTests and LoginUserTest are my tests

Note:
after my tests run, it does keep my tests in the passwd.txt file, so if they are already in there, they may
need to be deleted before running the tests again or the output may not be as expected.
