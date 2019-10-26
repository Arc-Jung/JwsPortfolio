#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.db import models
from django.contrib.auth.models import User


class UserModel(models.Model):
    UserId = models.CharField(primary_key=True, max_length=20) # AutoField
    Password = models.CharField(max_length=20)
    UserName = models.CharField(max_length=20)
    Phone = models.CharField(max_length=20)
    KakaoId = models.CharField(max_length=20)
    Email = models.CharField(max_length=50)
    Test777 = models.CharField(max_length=50)
    
    def __str__(self):
        return self.UserId