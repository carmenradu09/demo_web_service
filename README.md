# Runnin the application locally:

The application was developed and ran under Intellij IDEA,using Gradle as the build-automation system. Hence, in order to run the application:
-> run the bootRun task
-> in a browser, go to http://localhost:8080/ and browser confirms that aplication is running:
		{
		  "_links" : {
			"users" : {
			  "href" : "http://localhost:8080/users"
			},
			"profile" : {
			  "href" : "http://localhost:8080/profile"
			}
		  }
		}
Operations on users  are now available:
-> add: http://localhost:8080/demo/add?name=ion&email=ion@capgemini.com
		http://localhost:8080/demo/add?name=Luana&email=Luana@capgemini.com
		http://localhost:8080/demo/add?name=carmen&email=carmen@capgemini.com
		
-> get all users: http://localhost:8080/demo/all
		
-> update: http://localhost:8080/demo/add?name=carmen-elena&email=carmen@capgemini.com		
		
-> delete: http://localhost:8080/demo/add?name=carmen&email=carmen@capgemini.com


# Running application on Heroku: 
-> using https://demowebservice1.herokuapp.com/, the application can be tested in the same manner as locally.



