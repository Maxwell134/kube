pipeline {
    agent any

    environment {
        KUBECONFIG = "$WORKSPACE/kubeconfig"
    }

    stages {
        stage('Install Docker') {
            steps {
                sh '''
                sudo apt-get update
                sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common
                curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
                sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
                sudo apt-get update
                sudo apt-get install -y docker-ce
                sudo usermod -aG docker $USER
                '''
            }
        }

        stage('Install k3s') {
            steps {
                sh '''
                curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="--write-kubeconfig-mode 644" sh -
                '''
            }
        }

        stage('Configure KUBECONFIG') {
            steps {
                sh '''
                export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
                cp /etc/rancher/k3s/k3s.yaml $WORKSPACE/kubeconfig
                '''
            }
        }

        stage('Check Nodes') {
            steps {
                sh '''
                kubectl get nodes
                '''
            }
        }

        stage('Deploy Sample Application') {
            steps {
                sh '''
                cat <<EOF | kubectl apply -f -
                apiVersion: v1
                kind: Pod
                metadata:
                  name: nginx
                spec:
                  containers:
                  - name: nginx
                    image: nginx
                    ports:
                    - containerPort: 80
                EOF
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh '''
                kubectl get pods
                '''
            }
        }
    }

    post {
        always {
            sh 'sudo k3s-uninstall.sh'  // Clean up k3s after the build
        }
    }
}
