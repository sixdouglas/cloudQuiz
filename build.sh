#!/usr/bin/env bash

./mvnw install -DskipTests

if [[ $? != 0 ]]; then
    echo "Error during Maven build!"
    exit $?
fi

docker build -t quiz/config ./guardian/config/

if [[ $? != 0 ]]
then
    echo "Error during Config DOCKER build!"
    exit $?
fi

docker build -t quiz/admin ./guardian/admin/

if [[ $? != 0 ]]
then
    echo "Error during Admin DOCKER build!"
    exit $?
fi

docker build -t quiz/api ./api/

if [[ $? != 0 ]]
then
    echo "Error during Api DOCKER build!"
    exit $?
fi

docker build -t quiz/front ./front/

if [[ $? != 0 ]]
then
    echo "Error during Front DOCKER build!"
    exit $?
fi

docker-compose build
