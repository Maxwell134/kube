@Library('json-utils') _
import groovy.json.JsonSlurper

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
                    def pipelineJson = readFile('pipeline.json')
                    def pipelineConfig = new JsonSlurper().parseText(pipelineJson)
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
