# Prerequisites

To execute tests, you need to have java 8 installed + maven.
To generate an allure report, you need to have allure installed, for this, just follow
[this documentation](https://docs.qameta.io/allure/).

# Stack Used
As you can guess, all of it is written in java 8, with maven as build automation tool.\
Tests are run with junit 5.\
There is actually no unit tests since this project is used to execute end to end / integration tests.

# How to use it
To launch tests, you simply execute the command line:\
`mvn test -DparamFilePath={paramFilePath}`.\
By default paramFilePath is set to: `src/main/resources/param.properties.
A sample of property file can be found [there](src/main/resources/sample.properties).
If you want to give extra capabilities to your driver (saying you want to execute your tests remotely ?).\
You can find a sample of capabilities.json to fill [here](src/main/resources/sample-capabilities.json).

The maven plugin used to execute tests is [maven-surefire](http://maven.apache.org/surefire/maven-surefire-plugin/),
just read the documentation to execute special runs.


To generate tests report, just execute:\
`allure server {pathToAllureResult}`\
By default allure results are generated inside: `target/allure-results`
