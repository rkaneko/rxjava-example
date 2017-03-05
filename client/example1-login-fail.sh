#!/bin/bash

curl -X POST -H "Content-Type:application/json" -H "Accept:application/json" "localhost:8080/api/example1" -d @json/example1-login-fail.json
