node("master")
{
  stage("Git Repo")
  {
  
      git url: 'https://github.com/abhishekm8669/poc_automation.git'

	  
  }
  
  stage("Building and Pushing the image")
  {
     sh '
	       sudo docker build -t mlops-web-app ./project/        //building an Image using Dockerfile
	 '
	 
	 //Pushing the Docker image to DockerHub
	 
	 sh '''
	       
		   sudo docker tag mlops-web-app:latest devlogic123/image-repo:$BUILD_NUMBER
		   sudo docker push devlogic123/image-repo:$BUILD_NUMBER
	 '''
  }
  
  
}