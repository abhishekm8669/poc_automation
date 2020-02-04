node("master")
{
  stage("Connecting to Kubernetes Cluster")
  {
  
      git url: 'https://github.com/abhishekm8669/poc_automation.git'
/*	  sh '''
	        gcloud
	'''	  
  }
  
  stage("Configuring EFK")
  {
     sh '''
	       kubectl create -f EFK/elasticsearch.yaml
		   kubectl create -f EFK/fluentd-rbac.yaml
		   kubectl create -f EFK/fluentd-daemonset.yaml
		   kubectl create -f EFK/kibana.yaml
	 ''' */
	 
  }
  
  stage("Pulling image from DockerHub")
  {
     sh '''
	       sed -i 's/latest/'$BUILD_NUMBER'/g' Mlops/deploy.yaml
	'''
  
  }
  
  
}
