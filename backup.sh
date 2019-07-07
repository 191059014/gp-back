#!/usr/bin/env bash
fileDir="/data/jenkins/deploy/gp-back"
if [[ -d "$fileDir" ]]
then
   mv /data/jenkins/deploy/gp-back /data/jenkins/backup/gp-back/gp-back.`date +%Y%m%d%H%M%S`
   echo "backup last package success..."
fi
