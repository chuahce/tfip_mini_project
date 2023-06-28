# Set the base image
FROM node:16.14-alpine as build

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json files into the container
COPY package*.json ./

# Install the dependencies
RUN npm install --production

# Copy the entire application code into the container
COPY . .

# Build the Angular application for production
RUN npm run build -- --configuration=production

# Use a lightweight web server to serve the Angular application
FROM nginx:1.21.0-alpine

# Copy the built Angular application from the previous stage
COPY --from=build /app/dist/client /usr/share/nginx/html

# Expose the default HTTP port
EXPOSE 80

# Start the NGINX server
CMD ["nginx", "-g", "daemon off;"]
