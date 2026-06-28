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

       stage('Deploy Application') {
    steps {
        sh '''
        docker compose down -v || true

        docker pull ${IMAGE_NAME}:latest

        docker compose up -d
        '''
    }
    }
    }
}
