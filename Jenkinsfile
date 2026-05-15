pipeline {

    agent any

    tools {
        maven 'Maven'
    }

    environment {
        IMAGE_NAME = "rakshithamr10/course-app"
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
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
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

                    sh 'docker push $IMAGE_NAME'
                }
            }
        }

        stage('Deploy Container') {
            steps {

                sh 'docker stop course-container || true'
                sh 'docker rm course-container || true'

                sh '''
                docker run -d \
                --name course-container \
                -p 8080:8080 \
                rakshithamr10/course-app
                '''
            }
        }
    }
}
