node("master")
{
  stage("Connecting to Kubernetes Cluster")
  {
  
      git url: 'https://github.com/abhishekm8669/poc_automation.git'
	  sh '''
	        gcloud container clusters get-credentials standard-cluster-1 --zone us-central1-a --project kube-demo-261605
	'''	  
  }
  
  stage("Building and Pushing the image")
  {
     sh 'sudo docker build -t mlops-web-app ./project/'        //building an Image using Dockerfile
	 
	 //Pushing the Docker image to DockerHub
	 
     sh '''
		   sudo docker tag mlops-web-app:latest devlogic123/image-repo:$BUILD_NUMBER
		   sudo docker push devlogic123/image-repo:$BUILD_NUMBER
	'''
  }
  
  stage("Pulling image from DockerHub")
  {
     sh '''
	       sed -i 's/latest/'$BUILD_NUMBER'/g' Mlops/deploy.yaml
	'''
  
  }
 
  stage("Configuring EFK and Deploying Application")
  {
     sh '''
	       kubectl create -f Mlops/deploy.yaml
	       kubectl create -f EFK/elasticsearch.yaml
	       kubectl create -f EFK/fluentd-rbac.yaml
	       kubectl create -f EFK/fluentd-daemonset.yaml
	       kubectl create -f EFK/kibana.yaml
	       sleep 20
		   
	       kubectl get pods
	       kubectl get svc
	 ''' 
	 
  }
  
  stage("Configuring Prometheus and Grafana")
  {
     sh '''
	       kubectl create namespace monitoring
		   kubectl create -f prografana/prometheus-rbac.yaml
		   kubectl create -f prografana/config-map.yaml
		   kubectl create -f prografana/prometheus-deployment.yaml
		   kubectl create -f prografana/prometheus-service.yaml
		   
		   # Create State Metrics for Kubernetes Cluster
		   
		   kubectl create -f prografana/state-metrics-rbac.yaml
		   kubectl create -f prografana/state-metrics-deploy.yaml
		   kubectl create -f prografana/state-metrics-service.yaml
		   sleep 20
		   
		   # kubectl get pods -n kube-system | grep metrics
		   
		   # Create Deployment for Grafana
		   
		   kubectl create -f prografana/grafana-datasource-config.yaml
		   kubectl create -f prografana/grafana.yaml
		   sleep 20
		   
		   kubectl get pods -n monitoring
		   kubectl get svc -n monitoring
		   
    '''
  
  }
    
}
