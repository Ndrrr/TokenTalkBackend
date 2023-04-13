#!/bin/bash

# Loop through each directory in the current directory
for dir in discovery-server/ api-gateway/ */; do
  if [[ $dir != "postgresdata/" && $dir != "mongodbdata/" && $dir != "smartcontracts/" ]]; then # Exclude postgresdata, mongodbdata, and smartcontracts folders
    cd "$dir" # Change to the directory
    ./gradlew bootJar # Run "./gradlew bootJar"
    jarname=$(basename build/libs/*.jar) # Get the jar name dynamically
    java -jar build/libs/"$jarname" > output.log 2>&1 & # Redirect output to a file and run in the background
    cd .. # Change back to the parent directory
  fi
done