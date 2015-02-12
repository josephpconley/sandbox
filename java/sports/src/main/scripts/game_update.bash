#!/bin/bash
#
# dos2unix this file on itself once done editing to make readable to crontab
#
PATH=$PATH:/cygdrive/c/ant/bin;
export PATH

cd /cygdrive/c/tomcat/webapps/sports/scripts
ant -buildfile game_update.xml -verbose >> updates.log