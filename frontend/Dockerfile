# Phase 1: Building the Angular project
FROM node:20 AS build

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy all project contents into the container
COPY . .

# Build the Angular project in production mode
RUN npm run build --prod

# Step 2: Running with an Nginx server to serve the static files
FROM nginx:alpine

# Copy the files built by Angular to the Nginx directory
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Exposes port 80 to access the frontend
EXPOSE 80

# Command to start Nginx
CMD ["nginx", "-g", "daemon off;"]
