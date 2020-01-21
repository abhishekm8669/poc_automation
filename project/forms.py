from wtforms import Form, FloatField, validators
#from flask_wtf import FlaskForm

class LoginForm(Form):

	Sepal_Length = FloatField(label=' cm', validators=[validators.InputRequired()])
	Sepal_Width = FloatField(label=' cm', validators=[validators.InputRequired()])	
	Petal_Length = FloatField(label=' cm', validators=[validators.InputRequired()] )
	Petal_Width = FloatField(label=' cm', validators=[validators.InputRequired()] )
   
	
