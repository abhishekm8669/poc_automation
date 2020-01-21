from forms import LoginForm
from flask import Flask, render_template, request,url_for
from main import compute
import matplotlib.pyplot as plt
app = Flask(__name__)

@app.route('/displ',methods=['GET','POST'])
def display():
	img=url_for('static',filename='mat.png')
	return render_template('home.html',img=img)


@app.route('/', methods=['GET', 'POST'])
def index():
	form = LoginForm(request.form)
	if request.method == 'POST' and form.validate():
		result = compute(form.Sepal_Length.data, form.Sepal_Width.data,
form.Petal_Length.data, form.Petal_Width.data)

	else:
		result = None
	return render_template('login.html',form=form, result=result)

if __name__ == '__main__':
    app.run(host='0.0.0.0')

