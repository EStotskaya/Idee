#!/bin/bash
THISDIR=$(cd "$(dirname "$0")"; pwd)
cd $THISDIR

date="$(date +'%Y%m%d')"

nohup "${THISDIR}/webapp.sh" > ../logs/nohup.webapp.${date}.out 2>&1 &
nohup "${THISDIR}/agi.sh"    > ../logs/nohup.agi.${date}.out 2>&1 &

