GOAL

This app should have the ability to communicate with Joe's Feed database to provide 
updated SMS information.  A separate webapp will be built to allow Joe to read/send texts
from the webapp (and thus have the luxury of talking via text on the computer)

This SMS feed will be the first of many feeds to be managed by Joe's database.

TASKS
	1)	Populate Joe's Feed database with contacts from Google Contacts (these sync with the android phone)
			- no need to do this, will just use Google Contacts as my entity db
			
	1a)	Define model beans in Joe's Feed application and jar them  up for use in droid app
	
	2)	Write droid program to sync with feed database (i.e. put all thread/sms info into the db and tie it to Contacts)
	3)	Write listener to do syncing whenever new text comes in
	4)	Build web UI interface to clone texting capabilities
	