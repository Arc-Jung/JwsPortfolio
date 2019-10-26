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

import logging
logger = logging.getLogger('testlog')

####################################################################

def joinUser(request):
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
        
def joinUserJson(request): # ('UserId', 'Password', 'UserNameSet', 'Phone', 'KakaoId', 'Email')
    if request.method == "POST":
        JsonData = request.POST.JsonData
        #JsonData = json.loads(request.body.decode('utf-8'))
        #UserModel.UserId = JsonData['UserId']
        #UserModel.Password = JsonData['Password']
        #UserModel.UserNameSet = JsonData['UserNameSet']
        #UserModel.Phone = JsonData['Phone']
        #UserModel.KakaoId = JsonData['KakaoId']
        #UserModel.Email = JsonData['Email']
        return JsonResponse(JsonData)
    else:
        return HttpResponse('json 데이터가 존재하지 않습니다.')
    
def loginUser(request):
    return HttpResponse('성공 : 1')