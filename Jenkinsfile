pipeline {
    options {
            disableConcurrentBuilds()
        }
    agent any
        tools {
            maven 'Maven3'
        }
    stages {
        stage('BUILD'){
            steps{
                git 'https://github.com/v1llaDEV/api-chess-management.git'
                sh 'mvn clean package -Dmaven.test.skip'

            }
        }
        stage('DOCKERIZE'){
            steps{
                echo 'clean old containers'
                sh 'docker rm api-chess-management --force'
                echo 'clean old images'
                sh 'docker rmi api-chess-management --force'
                echo 'Build image'
                sh 'docker build . -t api-chess-management'
            }
        }

        stage('DEPLOY'){
            steps{
                 echo 'Deploying'
                  sh 'docker run -p 8080:8080 -d --name api-chess-management --network tomcat-mysql-network api-chess-management'
            }
        }
    }
}