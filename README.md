# The Zenjob Test Automation Framework ![N|Solid](https://www.zenjob.de/static/favicon-c3636ca5d17317a43135cf9077bd35fc.ico)

## Configuration

To configure the development environment, use a properties file, or environment variables.

### Example environment.properties file in src/test/resources

```
user=automationtester@zenjob.com
password=**********
showexecution=false
```

### Parameters

We include for the execution of the test's the parameters, we need use they to split the execution in diferents application to execute, enviroments, browsers, versions, sizes, platforms. 

At this moments we just include parameters 'Not Mandatories'. In case if we dont add any value, they are going to take the values descripted by parameter.

The current parameters at this moments are: 

env:
Of enviroment, this include the enviroment where we are going to execute the automation.

app:
Of Application, this include the application to execute in the test.

In this table we can see the options to include in the current parameters:

| Application/Parameter	| env 		 | app 			| 
| ---| ---| ---| 
| Company				| qa		 | Company 		| 
| Company				| staging	 | Company 		| 
| Operations			| qa		 | Operations	| 
| Operations			| staging	 | Operations	| 


Syntaxys of the execution command:

```
gradle cucumber -Dcucumber.options='--tags @[NAME_OF_TAG_TO_INCLUDE]' -Dapp=[APP_NAME] -Denv=[ENVIROMEN_NAME]
```

NAME_OF_TAG_TO_INCLUDE,
This is the tag to included in the feature file, we need to get this value from the file of the test.

APP_NAME,
Is the name of the application, right now is possible just the values "company" or "operation", by default this is going to take the value "ops"

ENVIROMEN_NAME,
In this case we need to set the values "qa" or "staging", by default this is going to take the value "staging"

Example of the command:

```
gradle cucumber -Dcucumber.options='--tags @localtest1' -Dapp=company -Denv=stage
gradle cucumber -Dcucumber.options='--tags @localtest1' -Dapp=company -Dbrowser=ie
gradle cucumber -Dcucumber.options='--tags @tid409' -Dapp=ops
```

### Browser parameter change

This is not a mandatory parameter, per default if we dont include this parameter, this is going to use chrome.

We need to include to make changes of the browser the parameter with the same name "browser". The options of this parameters at this moment are the next:

| browser	| 
| ---|
| chrome |
| firefox |
| edge |
| ie |

### Push results to TestRail

After `cucumber` task are finished, there is `results.json` file with run results automatically created in `build/reports` folder. In order to push results to the TestRail, there is a `pushResultsToTestRail` gradle task.
This task requires `runId` parameter to be set in order to push results to the specific TestRail run. 

Example:

```
gradle pushResultsToTestRail -DrunId=38
```


