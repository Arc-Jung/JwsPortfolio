#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.core.exceptions import ObjectDoesNotExist, MultipleObjectsReturned
from rest_framework import filters
from rest_framework import viewsets
from rest_framework import permissions
from rest_framework.response import Response
import json

from django.db import models
from .models import UserModel, ProductModel, SaleModel, LikeModel, SaleModel, ImageModel, UserImageModel, ProImageModel # Model import
from .serializers import UserSerializer, ProductSerializer, LikeSerializer, SaleSerializer, ImageSerializer, UserImageSerializer, ProImageSerializer # Serializer import
from django.http import JsonResponse
from django.http import HttpResponse
from django.contrib.auth import login, authenticate #20191002
from django.http import HttpResponse #20191004
from django.contrib.auth.models import User
from django.contrib import auth
from .forms import UserJoinForm

import logging
logger = logging.getLogger('testlog')

####################################################################


class UserViewSet(viewsets.ModelViewSet):
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer # JSON 으로 파싱

class ProductViewSet(viewsets.ModelViewSet):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer # JSON 으로 파싱

class LikeViewSet(viewsets.ModelViewSet):
    queryset = LikeModel.objects.all()
    serializer_class = LikeSerializer # JSON 으로 파싱
    
class SaleViewSet(viewsets.ModelViewSet):
    queryset = SaleModel.objects.all()
    serializer_class = SaleSerializer # JSON 으로 파싱    

class ImageViewSet(viewsets.ModelViewSet):
    queryset = ImageModel.objects.all()
    serializer_class = ImageSerializer # JSON 으로 파싱    
    
class UserImageViewSet(viewsets.ModelViewSet):
    queryset = UserImageModel.objects.all()
    serializer_class = UserImageSerializer # JSON 으로 파싱    
    
class ProImageViewSet(viewsets.ModelViewSet):
    queryset = ProImageModel.objects.all()
    serializer_class = ProImageSerializer # JSON 으로 파싱    
    
    
####################################################################