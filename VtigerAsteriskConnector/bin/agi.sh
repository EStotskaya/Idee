#!/bin/sh
THISDIR=$(cd "$(dirname "$0")"; pwd)
cd $THISDIR

JAVAJARNAME=vtigeragi.jar
JAVAJARPATH=../agi/vtigeragi.jar

# Kill old process
JAVAPID=$(ps ax | grep $JAVAJARPATH | grep -v grep | head -1 | awk '{print $1}')
if [ "$JAVAPID" != "" ]
then
    kill -15 $JAVAPID
fi

if [ "$1" != "stop" ]
then
    java -jar ../agi/$JAVAJARNAME
fi
