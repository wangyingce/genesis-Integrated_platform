#!/bin/bash
SPRINGSIDE_HOME=../../		

if test -z $JAVA_HOME; then
    echo '请先设置JAVA_HOME环境变量'
else
    "$JAVA_HOME/bin/java" -cp ../lib/ant/ant.jar:../lib/ant/ant-nodeps.jar:../lib/ant/ant-junit.jar:../lib/test/junit-3.8.1.jar:$JAVA_HOME/lib/tools.jar org.apache.tools.ant.Main -f ../build.xml $1
fi