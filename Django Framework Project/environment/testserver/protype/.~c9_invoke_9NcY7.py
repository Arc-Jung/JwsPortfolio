#-*- coding: utf-8 -*- 
# 한글을 사용하면 위에 꼭 써야함
from django.core.exceptions import ObjectDoesNotExist, MultipleObjectsReturned
from rest_framework import filters
from rest_framework import viewsets
from rest_framework import permissions
from rest_framework.response import Response

from django.db import models
from .models import UserModel, ProductModel, SaleModel, LikeModel # Model import
from .serializers import UserSerializer, ProductSerializer
from django.http import JsonResponse
from django.http import HttpResponse
from django.contrib.auth import login, authenticate #20191002
from django.http import HttpResponse #20191004
from django.contrib.auth.models import User
from django.contrib import auth

import logging
logger = logging.getLogger('testlog')

####################################################################


class UserViewSet(viewsets.ModelViewSet):
    queryset = UserModel.objects.all()
    serializer_class = UserSerializer # JSON 으로 파싱
    
    def create(self,request):
        UserCreateJson = {}
        if request.POST["Password"] == request.POST["Password"]:
            usersavelist = self.create_user(
                UserId
                UserNameSet=request.POST["UserNameSet"], 
                Password=request.POST["Password"]),
                Phone
                KakaoId
                Email
        
            return Response
        


####################################################################


class ProductViewSet(viewsets.ModelViewSet):
    queryset = ProductModel.objects.all()
    serializer_class = ProductSerializer # JSON 으로 파싱
    #permission_class = (permissions.IsAuthenticated)
    
    def productlist(self, request): # (self, request, *args): 8000/product?request=1
        if request.method == 'GET':
            ProductListJson = {} # 장고가 JSON 데이터를 담아서 보낼 배열
            ProductList = self.get_object();
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