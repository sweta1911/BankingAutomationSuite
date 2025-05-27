pipeline {
    agent any

    stages {
        stage('Start') {
            steps {
                echo '✅ Jenkinsfile executed'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("bankingautomationsuite:latest")
                }
            }
        }

        stage('Run Tests in Docker') {
            steps {
                script {
                    docker.image("bankingautomationsuite:latest").inside {
                        sh 'echo "Running tests inside container..."'
                        sh 'mvn clean test'
                    }
                }
            }
        }

        stage('Archive Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }
}
