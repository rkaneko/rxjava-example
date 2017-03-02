#!/bin/bash

curl -X POST -H "Content-Type:application/json" -H "Accept:application/json" "localhost:8080/api/recommendation" -d @json/recommendation.json
