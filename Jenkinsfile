// pipeline {
//     agent any
//
//     environment {
// //         MAVEN_OPTS = '-Xms256m -Xmx1G'
//         DOCKER_IMAGE = 'bankingautomationsuite:latest'
//     }
//
//     tools {
//         maven 'Maven 3'     // Must be defined in Jenkins Global Tool Configuration
//         jdk 'JDK 21'        // Also defined in Jenkins Global Tool Configuration
//     }
//
//     options {
//         timestamps()
//         buildDiscarder(logRotator(numToKeepStr: '10'))
//     }
//
//     parameters {
//         string(name: 'ENV', defaultValue: 'dev', description: 'Target environment: dev/test/prod')
//     }
//
//     stages {
//         stage('Preparation') {
//             steps {
//                 echo "🧪 Building for environment: ${params.ENV}"
//                 echo "🔀 Branch: ${env.GIT_BRANCH ?: 'master'}"
//             }
//         }
//
//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }
//
//         stage('Build') {
//             steps {
//                 sh 'mvn clean compile'
//             }
//         }
//
//         stage('Tests') {
//             parallel {
//                 stage('Unit Tests') {
//                     steps {
//                         sh 'mvn test'
//                     }
//                 }
//                 stage('Integration Tests') {
//                     steps {
//                         sh 'mvn verify -DskipUnitTests=true'
//                     }
//                 }
//             }
//         }
//         stage('Run Tests Inside Docker') {
//             steps {
//                 script {
//                     docker.image("${IMAGE_NAME}").inside {
//                         // Print hostname or OS info
//                         sh 'echo "Running inside container: $(hostname)"'
//                         sh 'cat /etc/os-release || ver' // For Linux/Windows
//                         sh 'mvn clean test'
//                     }
//                 }
//             }
//         }
//         stage('Package & Archive') {
//             steps {
//                 sh 'mvn package -DskipTests'
//                 archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
//             }
//         }
//
//         stage('Deploy to Environment') {
//             when {
//                 expression { return params.ENV != 'dev' }
//             }
//             steps {
//                 echo "🚀 Deploying to ${params.ENV}..."
//                 // Add your real deployment script/command here
//                 sh "echo 'Deploy logic to ${params.ENV} goes here.'"
//             }
//         }
//     }
//
//     post {
//         success {
//             echo '✅ Build succeeded!'
//             junit 'target/surefire-reports/*.xml'
//
//             emailext(
//                     subject: "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
//                     body: """
//                     <p>✅ <b>Build succeeded!</b></p>
//                     <ul>
//                         <li><b>Job:</b> ${env.JOB_NAME}</li>
//                         <li><b>Build Number:</b> ${env.BUILD_NUMBER}</li>
//                         <li><b>URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></li>
//                         <li><b>Environment:</b> ${params.ENV}</li>
//                     </ul>
//                     <p><b>🔍 Test Report:</b> <a href="${env.BUILD_URL}testReport">${env.BUILD_URL}testReport</a></p>
//                     <p><b>📦 Artifacts:</b> <a href="${env.BUILD_URL}artifact/">Download</a></p>
//                 """,
//                     mimeType: 'text/html',
//                     attachLog: true,
//                     recipientProviders: [
//                             [$class: 'DevelopersRecipientProvider'],
//                             [$class: 'RequesterRecipientProvider'],
//                             [$class: 'CulpritsRecipientProvider']
//                     ]
//             )
//         }
//
//         failure {
//             echo '❌ Build failed.'
//             junit 'target/surefire-reports/*.xml'
//
//             emailext(
//                     subject: "❌ FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
//                     body: """
//                     <p>❌ <b>Build failed!</b></p>
//                     <ul>
//                         <li><b>Job:</b> ${env.JOB_NAME}</li>
//                         <li><b>Build Number:</b> ${env.BUILD_NUMBER}</li>
//                         <li><b>URL:</b> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></li>
//                         <li><b>Environment:</b> ${params.ENV}</li>
//                     </ul>
//                     <p><b>🔧 Console Output:</b> <a href="${env.BUILD_URL}console">${env.BUILD_URL}console</a></p>
//                 """,
//                     mimeType: 'text/html',
//                     attachLog: true,
//                     recipientProviders: [
//                             [$class: 'DevelopersRecipientProvider'],
//                             [$class: 'RequesterRecipientProvider'],
//                             [$class: 'CulpritsRecipientProvider']
//                     ]
//             )
//         }
//
//         always {
//             cleanWs()
//         }
//     }
// }
pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'bankingautomationsuite:latest'
    }
    tools {
        maven 'Maven 3'     // Must be defined in Jenkins Global Tool Configuration
        jdk 'JDK 21'        // Also defined in Jenkins Global Tool Configuration
    }
    stages {
        stage('Hello') {
            steps {
                echo 'Pipeline started'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("my-docker-test-image")
                }
            }
        }
        stage('Run Tests Inside Docker') {
            steps {
                script {
                    docker.image("my-docker-test-image").inside {
                        sh 'echo "Running inside Docker container: $(hostname)"'
                        sh 'mvn -version' // Example test step
                    }
                }
            }
        }
    }
}
