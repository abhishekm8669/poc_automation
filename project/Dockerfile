FROM python:3.6.4-slim-jessie
RUN apt-get update
RUN apt-get install -y gcc g++ make libffi-dev python-pip git
RUN pip install --upgrade pip
RUN pip install pandas==0.22.0
RUN pip install sklearn tensorflow==1.14.0 seaborn matplotlib requests flask wtforms 
WORKDIR /home/master/project
COPY . .
RUN cd /home/master/project
RUN rm -rf Sagemaker_Data/
RUN git clone https://avhadutgadhave@gmail.com:Patil@005@github.com/avhadutgadhave/Sagemaker_Data.git
#RUN ipython nbconvert --to python main.ipynb
RUN pip install ipython nbconvert && ipython nbconvert --to python Sagemaker_Data/main.ipynb && cp Sagemaker_Data/main.py .
EXPOSE 5000
#CMD ["ipython","nbconvert","--to","python","Sagemaker_Data/main.ipynb"]
#CMD ["sh","-c","python3 run.py"]
