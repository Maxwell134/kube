pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Docker Tasks') {
            steps {
                script {
                    sh "groovy sample.groovy"
                }
            }
        }
    }
}
