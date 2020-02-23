[![Build Status](https://travis-ci.org/exam-beer/beer.svg?branch=master)](https://travis-ci.org/exam-beer/beer)
# beer
Dette er min eksamensoppgave i faget DevOps PGR301
Den inneholder en spring applikasjon som handler om øl.
Du kan lage øl og se på øl, til og med slette øl. 
Ved hver push til master, så sendes koden videre til travis-ci og blir bygget og testet. Hvis det blir godkjent så blir det laget et docker image som pushes til docker hub. 
Det er satt opp infrastruktur for å sende micrometrics til en influxdb database. Dette blir igjen hentet av en grafana container som visualiserer dataen.
Applikajsonen bruker h2 som internt minne. 
