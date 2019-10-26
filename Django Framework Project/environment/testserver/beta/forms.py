#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django import forms
from .models import UserModel


class UserJoinForm(forms.ModelForm):
    class Meta:
        model = UserModel
        fields = ('UserId', 'Password', 'UserNameSet', 'Phone', 'KakaoId', 'Email')
        
