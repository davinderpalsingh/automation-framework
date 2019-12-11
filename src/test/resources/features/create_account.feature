Feature:	Validation of New User signup workflow

Background:	
	Given I am on the homepage
	When I click on "Sign up for free" link in header
	Then I should be navigated to "Sign up" landing page
	
	
	
	Scenario: Verify behavior and proper validations are in place for input fields on signup page
	And I should see input fields for "Email Address" and "Create Password"
	When I click on "Email Address" input field
	Then I should see info tooltip with the text "Your email address will be used to log into your account."
	When I click on "Create Password"   