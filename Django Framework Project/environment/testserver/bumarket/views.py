#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
#from .Models import UserModel
from .models import UserModel, ProductModel, SaleModel, LikeModel, SaleModel, ImageModel, UserImageModel, ProImageModel # Model import
from django.core.exceptions import ObjectDoesNotExist, MultipleObjectsReturned
from rest_framework import filters
from rest_framework import viewsets
from rest_framework import permissions
from rest_framework.response import Response
from django.shortcuts import render
from rest_framework.decorators import api_view
import json

from django.db import models
from .serializers import ( UserSerializer, ProductSerializer, LikeSerializer, SaleSerializer, ImageSerializer, UserImageSerializer, ProImageSerializer,LoginSerializer ) # Serializer import
from django.http import JsonResponse
from django.http import HttpResponse
from django.contrib.auth import login, authenticate #20191002
from django.http import HttpResponse #20191004
from django.contrib.auth.models import User
from django.contrib import auth
from .forms import UserJoinForm
from django.views.decorators.csrf import csrf_exempt
from rest_framework.authtoken.models import Token

from rest_framework.decorators import api_view
from rest_framework.decorators import parser_classes
from rest_framework.parsers import JSONParser
from rest_framework.views import APIView

from rest_framework.permissions import AllowAny
from rest_framework.schemas import SchemaGenerator
from rest_framework_swagger import renderers
from rest_framework import status

from django.contrib.auth.models import User
from rest_framework import generics
from django.contrib.auth import authenticate
from knox.models import AuthToken
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.pagination import PageNumberPagination
from django.db.models.query import QuerySet

####################################################################
        
class UserList(generics.ListCreateAPIView): # 유저의 정보를 조회, 추가
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer
  
####################################################################

class UserDetail(generics.ListCreateAPIView): # 특정한 유저의 정보를 자세히 조회
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer
    filter_backends = (filters.SearchFilter,)
    search_fields = ('UserId',)
    
####################################################################
        
class ProductList(generics.ListCreateAPIView ): # 상품의 정보를 조회, 추가, 상품리스트를 출력
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer
    pagination_class = PageNumberPagination

####################################################################

class category(generics.ListCreateAPIView): # 삼품의 특정 카테고리에 상품을 추가, 조회
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer
    filter_backends = (filters.SearchFilter,)
    search_fields = ('ProductCategory',)
    
####################################################################

class Login(generics.GenericAPIView): # 로그인 기능 개인정보를 다루기때문에 오직 POST 방식으로만 요청을 받음
    serializer_class = LoginSerializer
    
    def post(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        user = serializer.validated_data
        # 위의 validated_data로써 계정 인증을 
        return Response({
            "user": UserSerializer(user,
            context=UserSerializer.get_serializer_context()).data,
            "token": AuthToken.objects.create(user)[1]
        })

####################################################################

class Regist(generics.GenericAPIView): # 회원가입 기능 개인정보를 다루기때문에 오직 POST 방식으로만 요청을 받음
    serializer_class = UserSerializer
    
## kwargs basically means it can take more arguments
    def post(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_excption=True)
        user = serializer.save() 
        # 위의 save 코드로 인해 data가 저장됨
        return Response({
            "user": UserSerializer(user,
            context=self.get_serializer_context()).data,
            "token": AuthToken.objects.create(user)[1]
        })
        
####################################################################

class ImageRequest(generics.ListCreateAPIView): # 상품 이미지의 리스트를 출력 하거나 특정 상품의 상품이지를 응답
    queryset = ProImageModel.objects.all()
    serializer_class = ProImageSerializer
    filter_backends = (filters.SearchFilter,)
    search_fields = ('ProductId_id__ProductId',)
    
####################################################################

class ImageUpload(generics.ListCreateAPIView): # 이미지를 업로드 하는 기능
    queryset = ImageModel.objects.all()
    serializer_class = ImageSerializer
    filter_backends = (filters.SearchFilter,)
    search_fields = ('=ImageId',)
    
####################################################################

class ProductType(generics.ListAPIView): # 상품의 종류르 입력, 조회하느 기능
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer
    filter_backends = (filters.SearchFilter,)
    search_fields = ('ProductType',)
    
####################################################################

class SearchAll(generics.ListAPIView): # 검색어를 입력하여 특정한 문자열이 들어간 모든 상품을 출력
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer
    filter_backends = (filters.SearchFilter,)
    search_fields = ('ProductName','ProductText')

####################################################################

class SaleHistory(generics.ListAPIView): # 특정한 유저의 상품 판매 내역을 모두 춣력
    #queryset = ProductModel.objects.filter(UserId_id='')
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer
    model = ProductModel
    filter_backends = (filters.SearchFilter,)
    search_fields = ('UserId_id__UserId',)
        
####################################################################

class Test(generics.ListAPIView): 
    serializer_class = ProductSerializer
    def get_queryset(self, *args, **kwargs): 
        qs = ProductModel.objects.all() 
        qs = qs.filter(UserId=self.request.GET.get)
        return qs
        
####################################################################