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
                    if (fileExists('pipeline.json')) {
                        def pipelineConfig = readJSON file: 'pipeline.json'
                        def imageName = pipelineConfig.imageName
                        def imageTag = pipelineConfig.imageTag
                        def dockerUsername = pipelineConfig.dockerUsername

                        def parser = load 'sample.groovy'
                        parser.runDockerTasks(imageName, imageTag, dockerUsername)
                    } else {
                        echo 'pipeline.json file not found. Skipping Docker tasks.'
                    }
                }
            }
        }
    }
}
