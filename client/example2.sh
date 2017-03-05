#!/bin/bash

curl -X POST -H "Content-Type:application/json" -H "Accept:application/json" "localhost:8080/api/example2" -d @json/example2.json
