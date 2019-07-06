#!/usr/bin/env bash
file="/data/jenkins/deploy/gp-back.jar"
if [[ -f "$file" ]]
then
   echo "backup last package start..."
   mv /data/jenkins/deploy/gp-back.jar /data/jenkins/backup/gp-back.jar.`date +%Y%m%d%H%M%S`
   echo "backup last package complete..."
fi
