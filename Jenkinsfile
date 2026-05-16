pipeline {

    agent any

    tools {
        maven 'maven'
    }

    environment {
        IMAGE_NAME = "rakshithamr10/course-app"
        CONTAINER_NAME = "course-container"
    }

    stages {

        stage('Clone Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Rakshitha1004/springboot_application.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:latest .'
            }
        }

        stage('Push Docker Image') {
            steps {

                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'

                    sh 'docker push ${IMAGE_NAME}:latest'
                }
            }
        }

        stage('Deploy Container') {
            steps {

                sh 'docker stop ${CONTAINER_NAME} || true'
                sh 'docker rm ${CONTAINER_NAME} || true'

                sh '''
                docker run -d \
                --name course-container \
                -p 8081:8080 \
                rakshithamr10/course-app:latest
                '''
            }
        }
    }
}
