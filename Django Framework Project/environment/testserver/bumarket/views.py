#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.core.exceptions import ObjectDoesNotExist, MultipleObjectsReturned
from rest_framework import filters
from rest_framework import viewsets
from rest_framework import permissions
from rest_framework.response import Response
from django.shortcuts import render
from rest_framework.decorators import api_view
import json

from django.db import models
from .models import UserModel, ProductModel, SaleModel, LikeModel, SaleModel, ImageModel, UserImageModel, ProImageModel # Model import
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

####################################################################
        
class UserList(generics.ListCreateAPIView):
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer
  
####################################################################

class UserDetail(generics.ListCreateAPIView):
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer
    filter_backends = (filters.SearchFilter,)
    search_fields = ('UserId',)
####################################################################
        
class ProductList(generics.ListCreateAPIView ):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer
    pagination_class = PageNumberPagination
    

    #def list(self, request):
    #    # Note the use of `get_queryset()` instead of `self.queryset`
    #    #productqueryset = self.get_queryset()
    #    productqueryset=ProductModel.objects.all()
    #    productserializer = ProductSerializer(productqueryset, many=True)
    #    return Response(productserializer.data)
        
    #def category(self, request):
    #    productqueryset = ProductModel.objects.all()
    #    productqueryset = productqueryset.filter(ProductCategory="request")
    #    productserializer = ProductSerializer(productqueryset, many=True)
    #    return Response(productserializer.data)

####################################################################

class category(generics.ListCreateAPIView):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer
    filter_backends = (filters.SearchFilter,)
    search_fields = ('ProductCategory',)
    
####################################################################

class Login(generics.GenericAPIView):
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

class Regist(generics.GenericAPIView):
    serializer_class = UserSerializer
    
## kwargs basically means it can take more arguments
    def post(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        user = serializer.save() 
        # 위의 save 코드로 인해 data가 저장됨
        return Response({
            "user": UserSerializer(user,
            context=self.get_serializer_context()).data,
            "token": AuthToken.objects.create(user)[1]
        })