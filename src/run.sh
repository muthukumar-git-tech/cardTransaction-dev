#!/bin/bash

# Set environment variables
export DB_ROOT_PASSWORD=myrootpassword
export DB_USER=mydbuser
export DB_PASSWORD=mydbpassword
export APP_SECRET=myappsecret

# Build the Docker images
echo "Building Docker images..."
docker-compose build

# Start the application stack
echo "Starting the application stack..."
docker-compose up -d

# Wait for a few seconds to ensure all services are up and running
sleep 10

# Display the status of the running containers
echo "Checking the status of running containers..."
docker-compose ps

echo "Application is up and running!"
