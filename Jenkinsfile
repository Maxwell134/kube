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
    
            def parser = load 'sample.groovy'
            parser.runDockerTasks(imageName, imageTag, dockerUsername)
        }
    }
}

    }
}
