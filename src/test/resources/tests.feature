Feature: Student Management

Scenario: Insert new student
    Given I have a student
    When I select option 2 from the menu
    And I enter the student name "John Doe"
    And I enter the student age 20
    And I enter the ID "12345"
    And I enter the number of courses 3
    And I enter the course names "Math, Science, History"
    Then the student should be added successfully
    And the student list should contain the student "John Doe" with ID "12345"

Scenario: Insert new student - Invalid name
    Given I have a student
    When I select option 2 from the menu
    And I enter the student name ""
    Then I should see an error message "Invalid name! Please, enter a valid name."

Scenario: Inser new student - Invalid age
    Given I have a student
    When I select option 2 from the menu
    And I enter the student name "John Doe"
    And I enter the student age -5
    Then I should see an error message "Invalid age! Please, enter a valid age."

Scenario: Insert new student - Invalid ID
    Given I have a student
    When I select option 2 from the menu
    And I enter the student name "John Doe"
    And I enter the student age 20
    And I enter the ID ""
    Then I should see an error message "Invalid ID! Please, enter a valid ID."

Scenario: Insert new student - Invalid number of courses
    Given I have a student
    When I select option 2 from the menu
    And I enter the student name "John Doe"
    And I enter the student age 20
    And I enter the ID "12345"
    And I enter the number of courses -1
    Then I should see an error message "Invalid number of courses! Please, enter a valid number."

Scenario: Insert new student - Repeated ID
    Given I have a student
    When I select option 2 from the menu
    And I enter the student name "John Doe"
    And I enter the student age 20
    And I enter the ID "12345"
    And already exists a student with ID "12345"
    Then I should see an error message "ID already exists! Please, enter a different ID."

Scenario: Consult student
    Given I have a student
    When I select option 1 from the menu
    And I enter the ID "U1111"
    Then I should see the student details:
        | Name             | Age | ID   | Courses               |
        | Leandro Santana  | 27  | U1111| Course n, Course n2   |

Scenario: Consult student - not found
    Given I have a student
    When I select option 1 from the menu
    And I enter the ID "U9999"
    Then I should see an error message "Student with ID U9999 not found."

Scenario: List all students
    Given I have a student
    When I select option 3 from the menu
    Then I should see the list of students:
        | Name             | Age | ID   | Courses                    |
        | Leandro Santana  | 27  | U1111| Course n, Course n2        |
        | Nathalia Carrer  | 37  | U2222| Course n3, Course n4       |
        | Leticia Rodrigues| 12  | U3333| Course n5, Course n5       |

Scenario: Invalid menu option
    Given I have a student
    When I select option 5 from the menu
    Then I should see an error message "Invalid option! Please, enter a valid option."

Scenario: InputMissMatch Exception
    Given I have a student
    When I select option 2 from the menu
    And I enter the student name "John Doe"
    And I enter the student age "twenty"
    Then I should see an error message "Invalid parameter! Please, try again."

Scenario: Exit program
    Given I have a student
    When I select option 4 from the menu
    Then I should see the message "Exiting the program..."