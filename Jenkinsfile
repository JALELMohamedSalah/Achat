pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'ahmed217/jenkins-test'
        // Déclarer imageTag au niveau global pour qu'il soit accessible dans toutes les étapes
        imageTag = "latest"
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.100.156:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "nexus"
    }

    stages {
        stage('Git Checkout') {
            steps {
                echo 'Pulling from Git...'
                git branch: 'ahmedL',
                url: 'https://github.com/JALELMohamedSalah/Achat.git'
            }
        }


stage('Clean and Build') {
            steps {
                script {
                     
                        sh 'mvn clean'
                        sh 'mvn package -DskipTests'
                    }
                }
            }
        


   stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                    dir('ahmedL') {
                        pom = readMavenPom file: "pom.xml"
                        filesByGlob = findFiles(glob: "target/*.${pom.packaging}")
                        echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                        artifactPath = filesByGlob[0].path
                        artifactExists = fileExists artifactPath
                        if (artifactExists) {
                            echo "* File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}"
                            nexusArtifactUploader(
                                nexusVersion: NEXUS_VERSION,
                                protocol: NEXUS_PROTOCOL,
                                nexusUrl: NEXUS_URL,
                                groupId: pom.groupId,
                                version: pom.version,
                                repository: NEXUS_REPOSITORY,
                                credentialsId: NEXUS_CREDENTIAL_ID,
                                artifacts: [
                                    [artifactId: pom.artifactId, classifier: '', file: artifactPath, type: pom.packaging],
                                    [artifactId: pom.artifactId, classifier: '', file: "pom.xml", type: "pom"]
                                ]
                            )
                        } else {
                            error "* File: ${artifactPath}, could not be found"
                        }
                    }
                }
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
                    // Utilisation de la variable imageTag déclarée dans la portée globale
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
                        // Utilisation de la variable imageTag déclarée dans la portée globale
                        sh "docker push ${DOCKER_IMAGE_NAME}:${imageTag}"
                    }
                }
            }
        }
    }
}