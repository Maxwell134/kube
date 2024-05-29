pipeline {
    agent any

    stages {
        stage('Read JSON') {
            steps {
                script {
                    // Read the JSON file
                    def jsonFile = readFile 'pipeline.json'
                    def jsonContent = readJSON text: jsonFile
                    
                    // Extract the variable from JSON
                    def imageName = jsonContent.dockerConfig.imageName
                    def tag = jsonContent.dockerConfig.imageTag
                    def dockerUsername = jsonContent.dockerConfig.dockerUsername
                    
                    
                    // Load the Groovy script
                    def call = load 'sample.groovy'
                    
                    // Call the hello method with the greeting message
                    call.runDockerTasks(imageName, Tag, dockerUsername)
                }
            }
        }
    }
}
