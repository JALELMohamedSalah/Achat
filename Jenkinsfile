pipeline {
    agent any
    stages {
        stage('Testing Maven') {
            steps {
                sh "mvn -version"
            }
        }

        stage('Git Checkout') {
            steps {
                echo 'Pulling from Git...'
                git branch: 'ahmedl',
                url: 'https://github.com/JALELMohamedSalah/Achat.git'
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
                    buildNumber = currentBuild.number
                    imageTag = "1.0.${buildNumber}"
                    withCredentials([usernamePassword(credentialsId: "DockerHubCredentials", usernameVariable: "DOCKERHUB_USERNAME", passwordVariable: "DOCKERHUB_PASSWORD")]) {
                        dockerImage = docker.build("ahmed217/jenkins-test:${imageTag}")
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: "DockerHubCredentials", usernameVariable: "DOCKERHUB_USERNAME", passwordVariable: "DOCKERHUB_PASSWORD")]) {
                        sh "docker login --username ${DOCKERHUB_USERNAME} --password ${DOCKERHUB_PASSWORD}"
                        sh "docker push ahmed217/jenkins-test:${imageTag}"
                    }
                }
            }
        }
    }
}