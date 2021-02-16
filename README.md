# flyway-without-java-bug

This repo proves that setting up a gradle configuration, with the right artifacts on the classpath, is not
propagated to flyway when the java plugin is not active.

Activating the java plugin just makes a timeout on the database (afterall there's no mysql on localhost... isn't it?)
as expected.

Master branch contains a reference to mysql driver that is not added to flyway tasks. This can be checked with --scan on
the builds or by running any tasks and waiting for `Unable to load class 'com.mysql.cj.jdbc.Driver'` error.

The branch [with-java-plugin](/TarodBOFH/flyway-without-java-bug/tree/with-java-plugin) contains the java plugin and
when running any task, the expected "connection timeout" error is raised instead of the missing driver.

To fix this, we can just move out of the `if(isJavaProject())` condition the configuration artifacts defined
in the build.
