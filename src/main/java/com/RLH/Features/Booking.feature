Feature: RLH Booking Feature
Scenario: RLH Booking test scenario

Given Navigate to the application
Then Validate the application title
Then select the location
     | Location |
     | Boston |
Then select the check-in and check-out dates
Then click on Check Availability link
Then select the room type
Then click on quick book link
Then validate the quick book URL
Then enter customer details
     | first name | last name | email | contact | postal code |
     | aditya | roy | adi@gmail.com | 9848022338 | 522237 |
Then enter payment details
     | credit card number | Expiration Date | cvv | postal code |
     | 4000400012345678 | 0823 | 123 | 522237 |
Then click on accept payment checkbox 
Then click on reserve room link
