#!/bin/bash

java -Djava.security.policy=/home/lumarzo/pod/javaconf/java.policy -Djava.rmi.server.useCodebaseOnly=false  -cp 'lib/jars/*' "ar.edu.itba.client.Client" $*

