#!/usr/bin/env bash
file="/data/jenkins/deploy/gp-back.jar"
if [[ -f "$file" ]]
then
   echo "backup project start..."
   mv /data/jenkins/deploy/gp-back.jar /data/jenkins/backup/gp-back.jar.`date +%Y%m%d%H%M%S`
   echo "backup project complete..."
fi
   mv /var/lib/jenkins/workspace/gp-back/target/gp-back-*-SNAPSHOT.jar /data/jenkins/backup/gp-back.jar