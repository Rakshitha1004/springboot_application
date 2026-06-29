### Create Nginx config: On the Nginx server:
```
sudo vi /etc/nginx/sites-available/reverse-proxy
```
### Paste this
```
server {
    listen 80 default_server;
    listen [::]:80 default_server;

    server_name _;

    location / {
        proxy_pass http://3.110.191.224:8081;
        proxy_http_version 1.1;

        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

### Enable Configuration
```
sudo rm /etc/nginx/sites-enabled/default
sudo ln -s /etc/nginx/sites-available/reverse-proxy /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```



### To get the count of http request
```
http://localhost:8081/actuator/metrics/http.server.requests
```

### Deployment.yaml
```
apiVersion: apps/v1
kind: Deployment

metadata:
  name: spring-deployment

spec:
  replicas: 2

  selector:
    matchLabels:
      app: spring

  template:
    metadata:
      labels:
        app: spring

    spec:
      containers:
      - name: spring-container
        image: your-dockerhub-username/course-app:v1

        ports:
        - containerPort: 8080
```

### service.yaml
```
apiVersion: v1
kind: Service

metadata:
  name: spring-service

spec:
  selector:
    app: spring

  type: NodePort

  ports:
  - port: 80
    targetPort: 8080
    nodePort: 30080
```
