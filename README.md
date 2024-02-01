This is a maven local repo.

Adding to a project:

- In settings.gradle add mavenLocal() to the repositories.
- In the module level build.gradle add implementation('com.jmgjeremy:hhbase:{version}')
- Clone this project.
- Open a terminal in the project folder.
- Enter ./gradlew publish
- The files should be placed at c:/user/M2 usually a hidden file.

- If you make a change to the repo increment the build number and use ./gradlew publish again.
- update the gradle and sync.
