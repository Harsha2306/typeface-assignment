# Application Setup Guide

This README provides instructions on how to set up and run the application which consists of a Spring Boot backend with MongoDB database and a Node.js/NPM frontend.
Limited to jpg, png, pdf formats
## Prerequisites

Before you begin, make sure you have the following installed:

- [Docker](https://www.docker.com/products/docker-desktop/) and Docker Compose
- [Java JDK 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [Node.js and NPM](https://nodejs.org/)
- Git (optional)

## Repository Structure

```
/project-root
  ├── /backend         # Spring Boot backend
  │   ├── pom.xml
  │   └── src/...
  ├── /frontend        # Node.js frontend
  │   ├── package.json
  │   └── src/...
  └── docker-compose.yml
```

## Step 1: Clone the Repository

```bash
git clone https://github.com/Harsha2306/typeface-assignment.git
cd typeface-assignment
```

## Step 2: Start MongoDB with Docker

First, we need to start the MongoDB database using Docker:

```bash
docker-compose up -d
```

Verify that MongoDB is running:

```bash
docker ps
```

You should see a MongoDB container running on port 27017.

## Step 3: Build and Run the Backend

Navigate to the backend directory:

```bash
cd backend
```

Build the application with Maven:

```bash
mvn clean install
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The backend API should now be running on http://localhost:8080

## Step 4: Install and Run the Frontend

Open a new terminal window, navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend should now be running on http://localhost:5173

## Step 5: Access the Application

Open your browser and go to:

```
http://localhost:5173
```

## Stopping the Application

To stop the frontend: Press `Ctrl+C` in the terminal where the frontend is running.

To stop the backend: Press `Ctrl+C` in the terminal where the backend is running.

To stop the MongoDB container:

```bash
docker-compose stop
```

If you want to completely remove the containers and volumes:

```bash
docker-compose down -v
```
