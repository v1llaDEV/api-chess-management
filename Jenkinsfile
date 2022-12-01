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
                echo 'Building ....123'
                git branch : '%BRANCH_NAME%', url: 'https://github.com/v1llaDEV/api-chess-management.git'
                bat 'mvn clean package -Dmaven.test.skip'

            }
        }
        stage('DOCKERIZE'){
            steps{
                echo 'clean old containers'
                bat 'docker rm api-chess-management --force'
                echo 'clean old images'
                bat 'docker rmi api-chess-management --force'
                echo 'Build image'
                bat 'docker build . -t api-chess-management'
            }
        }

        stage('DEPLOY'){
            steps{
                 echo 'Deploying'
                  bat 'docker run -p 8080:8080 -d --name api-chess-management --network tomcat-mysql-network api-chess-management'
            }
        }
    }
}