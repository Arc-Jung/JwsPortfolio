#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
#상대방에게 API로 데이터를 제공하기 위해 JSON, XML과 같이 범용적으로 사용되는 데이터 포맷으로 바꿔 줄 필요가 있다.
#serializer는 django의 models 객체나 querysets 데이터를 그러한 JSON 포맷으로 변환하는 역할을 한다.
from rest_framework import serializers
from .models import UserModel, ProductModel, LikeModel, SaleModel, UserImageModel, ProImageModel, ImageModel
from django.contrib.auth.models import User
from django.contrib.auth import authenticate

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserModel
        fields = ('UserId', 'Password', 'UserNameSet', 'Phone', 'KakaoId', 'Email')

class ProductSerializer(serializers.ModelSerializer):
    class Meta:
        model = ProductModel
        fields = ('ProductId', 'ProductName', 'ProductCategory', 'ProductPrice', 'ProductDate', 'ProductText', 'ProductType', 'UserId')
        
class LikeSerializer(serializers.ModelSerializer):
    class Meta:
        model = LikeModel
        fields = ('Like','UserId', 'ProductId')
        
class SaleSerializer(serializers.ModelSerializer):
    class Meta:
        model = SaleModel
        fields = ('SaleId', 'SaleDate', 'ProductId')
        
class ImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = ImageModel
        fields = ('ImageId', 'ImageName', 'ImageSize', 'ImageSize', 'ImageLink')

class UserImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserImageModel
        fields = ('UserImageModelId', 'UserId','ImageId')

class ProImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = ProImageModel
        fields = ('ProImageModelId', 'ImageId', 'ProductId')
        
class LoginSerializer(serializers.Serializer):
    UserId = serializers.CharField()
    Password = serializers.CharField()
 
    def validate(self, data):
        user = authenticate(**data)
        if user and user.is_active:
            return user
        raise serializers.ValidationError("Incorrect Credentials")