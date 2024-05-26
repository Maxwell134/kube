import groovy.json.JsonSlurper

// Load and parse pipeline.json
def pipelineConfig = new JsonSlurper().parseText(new File('pipeline.json').text)

// Define a method to handle Docker-related tasks
def runDockerTasks(imageName, imageTag, dockerUsername) {
    def dockerImage = "${imageName}:${imageTag}"
    def dockerImageWithTag = "${dockerUsername}/${dockerImage}"
    
    // Check if the Docker container exists and delete it if it does
    if (sh(script: "docker ps -a --format '{{.Names}}' | grep -Eq '^${imageName}\$'", returnStatus: true)) {
        sh "docker rm -f ${imageName}"
    }
    
    // Build the Docker image
    sh "docker build -t ${dockerImage} ."
    
    // Tag the Docker image with the Docker Hub username
    sh "docker tag ${dockerImage} ${dockerImageWithTag}"
    
    // Push the Docker image to the registry
    sh "docker push ${dockerImageWithTag}"
}

// Call the method with values from pipeline.json
runDockerTasks(pipelineConfig.imageName, pipelineConfig.imageTag, pipelineConfig.dockerUsername)
