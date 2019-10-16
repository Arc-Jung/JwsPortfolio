#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.db import models
from django.contrib.auth.models import User


class UserModel(models.Model):
    UserId = models.CharField(primary_key=True, max_length=20)
    Password = models.CharField(max_length=20)
    UserName = models.CharField(max_length=20)
    Phone = models.CharField(max_length=20)
    KakaoId = models.CharField(max_length=20)
    Email = models.CharField(max_length=50)
    
    def __str__(self):
        return self.UserId
    
class ProductModel(models.Model):
    ProductId = models.AutoField(primary_key=True,max_length=20)
    ProductName = models.CharField(max_length=50)
    ProductCategory = models.CharField(max_length=20)
    ProductPrice = models.CharField(max_length=20)
    ProductDate = models.DateTimeField(auto_now_add=True)
    ProductText = models.TextField # Long Text
    ProductType = models.CharField(max_length=20)
    #UserId = models.CharField(max_length=20) # 외래키
    
    def __str__(self):
        return self.ProductName    

class LikeModel(models.Model):
    Like = models.AutoField(primary_key=True)
    #UserId = models.CharField(max_length=20) # 외래키
    #ProductId = models.CharField(max_length=20) # 외래키

class SaleModel(models.Model):
    SaleId = models.AutoField(primary_key=True)
    SaleDate = models.DateTimeField(max_length=20)
    #ProductId = models.CharField(max_length=20) # 외래키
    
    def __str__(self):
        return self.SaleId    
    
class UserImageModel(models.Model):
    NullFiled = models.CharField(max_length=20)
    #UserId = models.CharField(primary_key=True) # 외래키
    #ImageId = models.CharField(max_length=20) # 외래키
    
    def __str__(self):
        return self.NullFiled    
    
class ProImageModel(models.Model):
    NullFiled = models.CharField(max_length=20)
    #ImageId = models.CharField(max_length=20) # 외래키
    #ProductId = models.CharField(max_length=20) # 외래키
    
    def __str__(self):
        return self.NullFiled    
    
class ImageModel(models.Model):
    ImageId = models.AutoField(primary_key=True)
    ImageName = models.TextField # Long Text
    ImageSize = models.TextField # Long Text
    ImageLink = models.TextField # Long Text

    def __str__(self):
        return self.ImageName
