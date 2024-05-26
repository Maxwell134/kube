pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout your source code here
                // e.g., checkout scm
            }
        }
        
        stage('Run Docker Tasks') {
            steps {
                script {
                    // Read the JSON file using the correct method readJSON
                    def pipelineConfig = readJSON file: 'pipeline.json'
                    
                    // Load the runDockerTasks script
                    def dockerTasks = load 'dockerTasks.groovy'
                    
                    // Call the runDockerTasks method with values from pipeline.json
                    dockerTasks.runDockerTasks(pipelineConfig.imageName, pipelineConfig.imageTag, pipelineConfig.dockerUsername)
                }
            }
        }
    }
}
