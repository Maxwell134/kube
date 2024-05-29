pipeline {
    agent any

    stages {
        stage('Read JSON') {
            steps {
                script {
                    // Read the JSON file
                    def jsonFile = readFile 'pipeline.json'
                    def jsonContent = readJSON text: jsonFile
                    
                    // Extract the variables from JSON
                    def imageName = jsonContent.imageName
                    def imageTag = jsonContent.imageTag
                    def dockerUsername = jsonContent.dockerUsername
                    
                    // Load the Groovy script
                    def call = load 'sample.groovy'
                    
                    // Call the runDockerTasks method with the Docker-related parameters
                    call.runDockerTasks(imageName, imageTag, dockerUsername)
                }
            }
        }
    }
}
