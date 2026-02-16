#!/bin/bash
cd /home/kavia/workspace/code-generation/kotlin-calculator-app-10091-10100/kotlin_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

