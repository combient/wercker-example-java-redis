# getting started java redis

This is an absolutely minimal example of a Java-based multi-docker application.  It consists of two containers: 

- a REST api service and 
- a REDIS key store service. 

The REST api contains one resource which increments a page-hit counter in the REDIS service and prints a greeting 
containing this counter in a tex/html response.




