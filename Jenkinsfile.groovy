pipeline {
    agent any

    tools {
        maven 'Maven 3'  // Define in Jenkins global tools config
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/yourusername/BankingAutomationSuite.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}

