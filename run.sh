#!/bin/bash

set -e

./mvnw clean package azure-functions:run
