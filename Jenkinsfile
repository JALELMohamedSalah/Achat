pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'ahmed217/jenkins-test'
    }

    stages {
        stage('Git Checkout') {
            steps {
                echo 'Pulling from Git...'
                git branch: 'ahmedL',
                url: 'https://github.com/JALELMohamedSalah/Achat.git'
            }
        }

        stage('Testing Maven') {
            steps {
                sh "mvn -version"
            }
        }

        stage('Maven Clean') {
            steps {
                sh "mvn clean"
            }
        }

        stage('Maven Compile') {
            steps {
                sh "mvn compile"
            }
        }

        stage('Maven Package') {
            steps {
                sh "mvn package"
            }
        }

        stage('Maven Verify') {
            steps {
                sh "mvn verify"
            }
        }

        stage('Maven Install') {
            steps {
                sh "mvn install"
            }
        }

        stage('Docker version') {
            steps {
                sh "docker --version"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def buildNumber = currentBuild.number
                    def imageTag = "1.0.${buildNumber}"
                    
                    withDockerRegistry([credentialsId: "dockerHubCred", url: "https://index.docker.io/v1/"]) {
                        def dockerImage = docker.build("${DOCKER_IMAGE_NAME}:${imageTag}")
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withDockerRegistry([credentialsId: "dockerHubCred", url: "https://index.docker.io/v1/"]) {
                        sh "docker push ${DOCKER_IMAGE_NAME}:${imageTag}"
                    }
                }
            }
        }
    }
}
