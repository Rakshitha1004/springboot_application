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

###service.yaml
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
