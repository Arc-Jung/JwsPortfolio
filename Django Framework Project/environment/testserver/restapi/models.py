#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.db import models
from django.contrib.auth.models import User
from django.utils import timezone # DB 시간설정 
from datetime import date # DB 시간 설정

class UserModel(models.Model):
    UserId = models.CharField(primary_key=True, max_length=20) # AutoField
    Password = models.CharField(max_length=20)
    UserNameSet = models.CharField(max_length=20)
    Phone = models.CharField(max_length=20)
    KakaoId = models.CharField(max_length=20)
    Email = models.CharField(max_length=50)
    
    def __str__(self):
        return self.UserId
        
class ProductModel(models.Model):
    ProductId = models.CharField(primary_key=True,max_length=20)
    ProductName = models.CharField(max_length=50)
    ProductCategory = models.CharField(max_length=20)
    ProductPrice = models.CharField(max_length=20)
    ProductDate = models.DateTimeField(auto_now_add=True, null=True) # 해당 레코드 생성시 현재 시간 자동저장
    ProductText = models.CharField(max_length=300)
    ProductType = models.CharField(max_length=20)
    UserId = models.ForeignKey(UserModel, on_delete = models.CASCADE) # 외래키 설정 이미지를 삭제 했을 때 연관 항목을 어떻게 할지 설정하는 옵션 : 자동 삭제로 설정
    
    def __str__(self):
        return self.ProductId

class LikeModel(models.Model):
    Like = models.AutoField(primary_key=True)
    UserId = models.ForeignKey(UserModel, on_delete = models.CASCADE) # 외래키 설정 이미지를 삭제 했을 때 연관 항목을 어떻게 할지 설정하는 옵션 : 자동 삭제로 설정
    ProductId = models.ForeignKey(ProductModel, on_delete = models.CASCADE) # 외래키 설정 이미지를 삭제 했을 때 연관 항목을 어떻게 할지 설정하는 옵션 : 자동 삭제로 설정
    
    def __str__(self):
        return self.UserId

class SaleModel(models.Model):
    SaleId = models.AutoField(primary_key=True, max_length=20)
    SaleDate = models.DateTimeField(default=date.today, null=True, blank=True)
    ProductId = models.ForeignKey(ProductModel, on_delete = models.CASCADE) # 외래키 설정 이미지를 삭제 했을 때 연관 항목을 어떻게 할지 설정하는 옵션 : 자동 삭제로 설정
    
    def __str__(self):
        return self.SaleId
    
class ImageModel(models.Model):
    ImageId = models.CharField(primary_key=True, max_length=20)
    ImageName = models.CharField(max_length=300)
    ImageSize = models.CharField(max_length=300)
    ImageLink = models.CharField(max_length=300)

    def __str__(self):
        return self.ImageName
    
class UserImageModel(models.Model):
    UserImageModelId = models.AutoField(primary_key=True)
    UserId = models.ForeignKey(UserModel, on_delete = models.CASCADE) # 외래키 설정 이미지를 삭제 했을 때 연관 항목을 어떻게 할지 설정하는 옵션 : 자동 삭제로 설정
    ImageId = models.ForeignKey(ImageModel, on_delete = models.CASCADE) # 외래키 설정 이미지를 삭제 했을 때 연관 항목을 어떻게 할지 설정하는 옵션 : 자동 삭제로 설정
    
    def __str__(self):
        return self.UserId
    
class ProImageModel(models.Model):
    ProImageModelId = models.AutoField(primary_key=True)
    ImageId = models.ForeignKey(ImageModel, on_delete = models.CASCADE) # 외래키 설정 이미지를 삭제 했을 때 연관 항목을 어떻게 할지 설정하는 옵션 : 자동 삭제로 설정
    ProductId = models.ForeignKey(ProductModel, on_delete = models.CASCADE) # 외래키 설정 이미지를 삭제 했을 때 연관 항목을 어떻게 할지 설정하는 옵션 : 자동 삭제로 설정
    
    def __str__(self):
        return self.ProductId