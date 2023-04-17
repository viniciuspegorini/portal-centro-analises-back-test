pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'Java'
    }
    stages {
        stage('Clean') {
            steps {
                echo 'mvn cleaning'
                sh 'mvn clean'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn package'
            }
        }
        stage('Run') {
            steps {
                echo 'Running...'
                sh '''
                    java -jar target/my-spring-boot-app-*.jar &
                    APP_PID=$!
                    sleep 30
                    kill $APP_PID
                '''
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                script {
                    docker.withRegistry('aqui o docker registry', 'aqui as creds') {
                        def appImage = docker.build("oficina-api", "./")
                        appImage.push()
                    }
                }
            }
        }
    }
    post {
        always {
            echo 'Build finished.'
        }
        success {
            echo 'Build succeeded.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
