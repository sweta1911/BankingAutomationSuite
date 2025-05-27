pipeline {
    agent any

    stages {
        stage('Start') {
            steps {
                echo '✅ Jenkinsfile loaded successfully!'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("banking-tests")
                }
            }
        }

        stage('Run Tests in Docker') {
            steps {
                script {
                    docker.image("banking-tests").inside {
                        sh 'echo "🧪 Running tests inside Docker container..."'
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
