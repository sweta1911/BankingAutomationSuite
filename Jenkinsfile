pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/sweta1911/BankingAutomationSuite.git'
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
                        sh 'echo "✅ Running tests inside Docker container..."'
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
