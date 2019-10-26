#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.core.exceptions import ObjectDoesNotExist, MultipleObjectsReturned
from rest_framework import filters
from rest_framework import viewsets
from rest_framework import permissions
from rest_framework.response import Response
import json

from django.db import models
from .models import UserModel, ProductModel, SaleModel, LikeModel # Model import
from .serializers import UserSerializer, ProductSerializer
from django.http import JsonResponse
from django.http import HttpResponse
from django.contrib.auth import login, authenticate #20191002
from django.http import HttpResponse #20191004
from django.contrib.auth.models import User
from django.contrib import auth
from .forms import UserJoinForm

from django.views.decorators.csrf import csrf_exempt

import logging
logger = logging.getLogger('testlog')

####################################################################

@csrf_exempt # CSRF 쿠키 보안 해제
def createUserForm(request):
    if request.method == "POST":
        form = UserJoinForm(request.POST)
        if form.is_valid():
            NewUser = UserModel.objects.create_user(**form.cleaned_data)
            login(request, NewUser)
            return HttpResponse('유저 생성이 완료되었습니다.')
        else:
            return HttpResponse('ID가 이미 존재합니다.')
    else:
        return HttpResponse('사용자명이 이미 존재합니다.')

####################################################################

@csrf_exempt # CSRF 쿠키 보안 해제
def user(request):
    serializer_class = UserSerializer  # ('UserId', 'Password', 'UserNameSet', 'Phone', 'KakaoId', 'Email')
    
    if request.method == "POST":
        JsonData = json.loads(request.body.decode('utf-8'))
        UserIdJson = JsonData[0]["UserId"]
        PasswordJson = JsonData[0]["Password"]
        UserNameSetJson = JsonData[0]["UserNameSet"]
        PhoneJson = JsonData[0]["Phone"]
        KakaoIdJson = JsonData[0]["KakaoId"]
        EmailJson = JsonData[0]["Email"]
        try:
            queryset = UserModel.objects.create(UserId = UserIdJson,Password = PasswordJson, UserNameSet=UserNameSetJson,Phone=PhoneJson,KakaoId=KakaoIdJson,Email=EmailJson)
            JsonResult = JsonData
            return HttpResponse(JsonResult)
        except Exception:
            JsonResult = 'SQL문에 오류가 존재합니다.'
            return HttpResponse(JsonResult)
    else:
        return HttpResponse('json 데이터가 존재하지 않거나 POST 방식이 아닙니다.')

####################################################################

@csrf_exempt # CSRF 쿠키 보안 해제
def loginUser(request):
    serializer_class = UserSerializer
    
    if request.method == "POST":
        JsonData = json.loads(request.body.decode('utf-8'))
        UserIdJson = JsonData[0]["UserId"]
        PasswordJson = JsonData[0]["Password"]
        #queryset.get(Password = 'PasswordJson') AND queryset.get(Password = 'PasswordJson')
        # 작성 중
        
    else:
        return HttpResponse('json 데이터가 존재하지 않거나 POST 방식이 아닙니다.')
        
####################################################################

@csrf_exempt # CSRF 쿠키 보안 해제
def createProduct(request): 
    serializer_class = ProductSerializer # ('ProductId', 'ProductName', 'ProductCategory', 'ProductPrice', 'ProductDate', 'ProductDate', 'ProductText', 'ProductType','UserId')
    
    if request.method == "POST":
        JsonData = json.loads(request.body.decode('utf-8'))
        #ProductIdJson = JsonData[0]["ProductId"] AUTO
        ProductNameJson = JsonData[0]["ProductName"]
        ProductCategoryJson = JsonData[0]["ProductCategory"]
        ProductPriceJson = JsonData[0]["ProductPrice"]
        #ProductDateJson = JsonData[0]["ProductDate"] AUTO
        ProductTextJson = JsonData[0]["ProductText"]
        ProductTypeJson = JsonData[0]["ProductType"]
        UserIdJson = JsonData[0]["UserId"]
        try:
            queryset = UserModel.objects.create(ProductName=ProductNameJson, ProductCategory=ProductCategoryJson, ProductPrice=ProductPriceJson, ProductText=ProductTextJson, ProductType=ProductTypeJson, UserId=UserIdJson)
            JsonResult = JsonData
            return HttpResponse(JsonResult)
        except Exception:
            JsonResult = 'SQL문에 오류가 존재합니다.'
            return HttpResponse(JsonResult)
    else:
        return HttpResponse('json 데이터가 존재하지 않거나 POST 방식이 아닙니다.')