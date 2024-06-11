pipeline {
    agent any

    environment {
        KUBECONFIG = "$WORKSPACE/kubeconfig"
        SUDO_PASSWORD = credentials('sudo-password') // Add a Jenkins credential for the sudo password
    }

    stages {
        stage('Install Docker') {
            steps {
                script {
                    def sudoCommand = 'echo "$SUDO_PASSWORD" | sudo -S'
                    sh """
                    $sudoCommand apt-get update
                    $sudoCommand apt-get install -y apt-transport-https ca-certificates curl software-properties-common
                    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | $sudoCommand apt-key add -
                    $sudoCommand add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu \$(lsb_release -cs) stable"
                    $sudoCommand apt-get update
                    $sudoCommand apt-get install -y docker-ce
                    $sudoCommand usermod -aG docker $USER
                    """
                }
            }
        }

        stage('Install k3s') {
            steps {
                sh 'curl -sfL https://get.k3s.io | INSTALL_K3S_EXEC="--write-kubeconfig-mode 644" sh -'
            }
        }

        stage('Configure KUBECONFIG') {
            steps {
                sh 'export KUBECONFIG=/etc/rancher/k3s/k3s.yaml && cp /etc/rancher/k3s/k3s.yaml $WORKSPACE/kubeconfig'
            }
        }

        stage('Check Nodes') {
            steps {
                sh 'kubectl get nodes'
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
                sh 'kubectl get pods'
            }
        }
    }

    post {
        always {
            sh 'sudo k3s-uninstall.sh'  // Clean up k3s after the build
        }
    }
}
