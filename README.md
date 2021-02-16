# flyway-without-java-bug

This repo proves that setting up a gradle configuration, with the right artifacts on the classpath, is not
propagated to flyway when the java plugin is not active.

Activating the java plugin just makes a timeout on the database (afterall there's no mysql on localhost... isn't it?)
as expected.

To fix this, we can just move out of the `if(isJavaProject())` condition the configuration artifacts defined
in the build.
