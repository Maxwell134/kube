FROM nginx:latest
WORKDIR /usr/share/nginx/html
COPY index.html .
USER root
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]
