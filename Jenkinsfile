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
            def pipelineConfig = readJSON file: 'pipeline.json'
            def imageName = pipelineConfig.imageName
            def imageTag = pipelineConfig.imageTag
            def dockerUsername = pipelineConfig.dockerUsername

            def parser = load 'sample.groovy'
            parser.runDockerTasks(imageName, imageTag, dockerUsername)
        }
    }
}

    }
}
