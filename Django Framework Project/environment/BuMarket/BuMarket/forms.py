from django import forms
from .models import UserModel


class UserJoinForm(forms.ModelForm):
    class Meta:
        model = UserModel
        fields = ('UserId', 'Password', 'UserName', 'Phone', 'KakaoId', 'Email')