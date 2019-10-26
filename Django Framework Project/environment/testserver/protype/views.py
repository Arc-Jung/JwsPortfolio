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


class UserViewSet(viewsets.ModelViewSet):
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer # JSON 으로 파싱
    
    def create(self, request):
        form = UserJoinForm(request.POST) # 안드로이드에서 POST 방식으로 보낸 JSON 데이터를 유저폼에 변환하여 저장
        result = {} # 장고가 JSON 데이터를 담아서 보낼 배열
        UserModel = form.save(commit=False)
        UserModel.UserId = request.UserId
        UserModel.Password = request.Password
        UserModel.UserNameSet = request.UserNameSet
        UserModel.Phone = request.Phone
        UserModel.KakaoId = request.KakaoId
        UserModel.Email = request.Email
        UserModel.save()
        return HttpResponse("성공")
        
        #idMultipleObject = True
        #UserModel.objects.create(UserId=userForm.data['UserId'])
        #idMultipleObject = False
        #UserModel.objects.create(Password=userForm.data['Password'])
        #UserModel.objects.create(UserNameSet=userForm.data['UserNameSet'])
        #UserModel.objects.create(Phone=userForm.data['Phone'])
        #UserModel.objects.create(KakaoId=userForm.data['KakaoId'])
        #UserModel.objects.create(Email=userForm.data['Email'])
        
        #user = userForm.save() # 저장
        #result['result'] = 'complete'
        #result['UserId'] = user.UserId
        #result['Password'] = user.Password
        #result['UserName'] = user.UserName
        #result['Phone'] = user.Phone
        #result['KakaoId'] = user.KakaoId
        #result['Email'] = user.Email
        #return JsonResponse(result)


####################################################################


class ProductViewSet(viewsets.ModelViewSet):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer # JSON 으로 파싱
    
    def productList(self, request): # (self, request, *args): 8000/product?request=1
        if request.method == 'GET':
            ProductListJson = {} # JSON 데이터를 담아서 보낼 배열을 선언
            ProductListJson = self.get_object();
            ProductListJson = ProductSerializer(ProductName = '000001')
            return Response(ProductListJson.data)
            #return HttpResponse("성공")
        else:
            return ''
        
    #def delete(self, request, *args, **kwargs):
    #    return ''
        
    #def sale(self, request, *args, **kwargs):
    #    return ''
        
    #def shop(self, request, *args, **kwargs):
    #    return ''
        
    #def serch(self, request, *args, **kwargs):
    #    return ''
     
     
####################################################################

