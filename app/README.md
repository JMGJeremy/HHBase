- Publish to mavenLocal()
  - in the terminal at the base project folder type ./gradlew publish
  - The repo is added to user/.m2/repository

- Update Homework Helper
  - Sync gradle files and run

- Publish to jitpack.io
  - Push your changes to the github (JMGJeremy/HHBase)
  - Click releases
  - Draft new release
  - Choose tag - increment the version number
  - release title = new tag
  - Go to jitpack.io
  - sign in 
  - Look up jmgjeremy/hhbase
  - click get it (this takes a few minutes for the module to build)
  - follow the directions to implement the module in the gradle