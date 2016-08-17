#!/usr/bin/env bash
if [ -z $1 ]
    then
        echo "error - need to provide a description. \\
        Usage: new_flyway_script.sh <description>"
        exit 1;
fi
touch "V$(date +"%s")__$1.sql"